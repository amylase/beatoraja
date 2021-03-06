package bms.player.lunaticrave2;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import bms.player.beatoraja.*;

/**
 * LR2のスコアデータベースへのアクセスクラス
 * 
 * @author exch
 */
public class LunaticRave2ScoreDatabaseManager {

	private String rootpath;

	private String playerpath = "/LR2files/Database/Score/";

	private String rivalpath = "/LR2files/Rival/";

	private final QueryRunner qr = new QueryRunner();

	public LunaticRave2ScoreDatabaseManager(String path) throws ClassNotFoundException {
		rootpath = path;
	}

	public LunaticRave2ScoreDatabaseManager(String path, String player, String rival) throws ClassNotFoundException {
		rootpath = path;
		this.playerpath = player;
		this.rivalpath = rival;
	}

	public void createTable(String playername) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");

			String sql = "SELECT * FROM sqlite_master WHERE name = ? and type='table';";
			// conn.setAutoCommit(false);
			sql = "SELECT * FROM sqlite_master WHERE name = ? and type='table';";
			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			// scoreテーブル作成(存在しない場合)
			pstmt.setString(1, "player");
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				if (qr.query(conn, sql, new MapListHandler(), "player").size() == 0) {
					sql = "CREATE TABLE [player] ([id] TEXT NOT NULL," + "[hash] TEXT," + "[name] TEXT,"
							+ "[irid] INTEGER," + "[irname] TEXT," + "[playcount] INTEGER," + "[clear] INTEGER,"
							+ "[fail] INTEGER," + "[perfect] INTEGER," + "[great] INTEGER," + "[good] INTEGER,"
							+ "[bad] INTEGER," + "[poor] INTEGER," + "[playtime] INTEGER," + "[combo] INTEGER,"
							+ "[maxcombo] INTEGER," + "[grade_7] INTEGER," + "[grade_5] INTEGER,"
							+ "[grade_14] INTEGER," + "[grade_10] INTEGER," + "[grade_9] INTEGER," + "[trial] INTEGER,"
							+ "[option] INTEGER," + "[systemversion] INTEGER," + "[gradeversion] INTEGER,"
							+ "[trialversion] INTEGER," + "[scorehash] TEXT," + "PRIMARY KEY(id));";
					qr.update(conn, sql);

					sql = "insert into player "
							+ "(id, hash, name, irid, irname, playcount, clear, fail, perfect, great, good, bad, poor, playtime, combo, maxcombo, "
							+ "grade_7, grade_5, grade_14, grade_10, grade_9, trial, option, "
							+ "systemversion, gradeversion, trialversion, scorehash)"
							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					qr.update(conn, sql, playername, "", "", 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, "");
				}
			}
			pstmt.setString(1, "score");
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				if (qr.query(conn, sql, new MapListHandler(), "score").size() == 0) {
					sql = "CREATE TABLE [score] ([hash] TEXT NOT NULL," + "[clear] INTEGER," + "[perfect] INTEGER,"
							+ "[great] INTEGER," + "[good] INTEGER," + "[bad] INTEGER," + "[poor] INTEGER,"
							+ "[totalnotes] INTEGER," + "[maxcombo] INTEGER," + "[minbp] INTEGER,"
							+ "[playcount] INTEGER," + "[clearcount] INTEGER," + "[failcount] INTEGER,"
							+ "[rank] INTEGER," + "[rate] INTEGER," + "[clear_db] INTEGER," + "[op_history] INTEGER,"
							+ "[scorehash] TEXT," + "[ghost] TEXT," + "[clear_sd] INTEGER," + "[clear_ex] INTEGER,"
							+ "[op_best] INTEGER," + "[random] INTEGER," + "[date] INTEGER," + "[rseed] INTEGER,"
							+ "[complete] INTEGER," + "PRIMARY KEY(hash));";
					qr.update(conn, sql);
				}
			}
		} catch (SQLException e) {
			Logger.getGlobal().severe("スコアデータベース初期化中の例外:" + e.getMessage());
		} finally {
			try {
				if (rs != null /* && !rs.isClosed() */) {
					rs.close();
				}
				if (pstmt != null /* isClosedは使えないので && !pstmt.isClosed() */) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					// conn.rollback();
					conn.close();
				}
			} catch (SQLException e) {
				// どうしようもない
				Logger.getGlobal().severe("スコアデータベース初期化中の例外:" + e.getMessage());
			}
		}
	}

	/**
	 * ライバルスコアを最新状態にする
	 * 
	 * @param rivalId
	 *            ライバルID
	 * @param scores
	 *            スコアデータ
	 * @return 更新スコアデータ数
	 * @author KASAKON
	 */
	public int updateRivalData(String rivalId, List<IRScoreData> scores) {
		int num = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + rivalpath + rivalId + ".db");
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();

			for (IRScoreData score : scores) {
				// ハッシュが存在し、スコアの更新があったか？
				String sql = "SELECT hash FROM rival WHERE hash = '" + score.getHash() + "' and (r_clear != "
						+ score.getClear() + " or r_maxcombo != " + score.getCombo() + " or r_perfect != "
						+ score.getPg() + " or r_great != " + score.getGr() + " or r_minbp != " + score.getMinbp()
						+ ");";
				ResultSet rs = stmt.executeQuery(sql);
				boolean isUpdate = false;
				boolean isExist = false;

				isUpdate = rs.next();
				if (isUpdate == true) {
					// 更新処理
					sql = "UPDATE rival SET r_clear = " + score.getClear() + ", r_maxcombo = " + score.getCombo()
							+ ", r_perfect = " + score.getPg() + ", r_great = " + score.getGr() + ", r_good = "
							+ score.getGd() + ", r_bad = " + score.getBd() + ", r_poor = " + score.getPr()
							+ ", r_minbp = " + score.getMinbp() + ", r_option = " + score.getOption()
							+ ", r_lastupdate = " + score.getLastupdate() + " WHERE hash = '" + score.getHash() + "';";
					stmt.executeUpdate(sql);
					num++;
				} else {
					// ハッシュが存在するか？
					sql = "SELECT hash FROM rival WHERE hash = '" + score.getHash() + "';";
					rs = stmt.executeQuery(sql);
					isExist = rs.next();
					if (isExist == false) {
						// 新規追加処理
						sql = "INSERT INTO rival VALUES ('" + score.getHash() + "'," + score.getClear() + ","
								+ score.getNotes() + "," + score.getCombo() + "," + score.getPg() + "," + score.getGr()
								+ "," + score.getGd() + "," + score.getBd() + "," + score.getPr() + ","
								+ score.getMinbp() + "," + score.getOption() + "," + score.getLastupdate() + ");";
						stmt.executeUpdate(sql);
						num++;
					}
				}
			}
			con.commit();
			// クローズ処理
			stmt.close();
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("ライバルデータ更新時の例外:" + e.getMessage());
		}
		return num;
	}

	public IRScoreData getScoreData(String playername, String hash, boolean grade) {
		Connection con = null;
		IRScoreData result = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			ResultSetHandler<List<IRScoreData>> rh = new BeanListHandler<IRScoreData>(IRScoreData.class);
			List<IRScoreData> score;
			if (grade) {
				score = qr
						.query(con,
								"SELECT hash, clear, clear_ex as exclear , totalnotes as notes, maxcombo as combo, rank, perfect as pg, great as gr  , minbp, "
										+ "playcount, clearcount, op_best as option FROM score WHERE hash LIKE '%"
										+ hash + "'", rh);
			} else {
				score = qr
						.query(con,
								"SELECT hash, clear, clear_ex as exclear , totalnotes as notes, maxcombo as combo, rank, perfect as pg, great as gr  , minbp, "
										+ "playcount, clearcount, op_best as option FROM score WHERE hash = '"
										+ hash
										+ "'", rh);
			}
			if (score.size() > 0) {
				IRScoreData sc = null;
				for (IRScoreData s : score) {
					if (sc == null || s.getClear() > sc.getClear()) {
						sc = s;
					}
				}
				result = sc;
			}
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("スコア" + playername + "取得時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	/**
	 * プレイヤースコアデータを取得する
	 * 
	 * @param hashes
	 *            スコアを取得する楽曲のhash
	 * @return <ハッシュ, スコアデータ>のマップ
	 */
	public Map<String, IRScoreData> getScoreDatas(String playername, String[] hashes, boolean grade) {
		Map<String, IRScoreData> result = new HashMap<String, IRScoreData>();
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			ResultSetHandler<List<IRScoreData>> rh = new BeanListHandler<IRScoreData>(IRScoreData.class);
			for (String hash : hashes) {
				List<IRScoreData> score;
				if (grade) {
					score = qr
							.query(con,
									"SELECT hash, clear, clear_ex as exclear ,totalnotes as notes,  maxcombo as combo, rank, perfect as pg, great as gr  , minbp, "
											+ "playcount, clearcount, op_best as option FROM score WHERE hash LIKE '%"
											+ hash + "'", rh);
				} else {
					score = qr
							.query(con,
									"SELECT hash, clear, clear_ex as exclear ,totalnotes as notes,  maxcombo as combo, rank, perfect as pg, great as gr  , minbp, "
											+ "playcount, clearcount, op_best as option FROM score WHERE hash = '"
											+ hash + "'", rh);
				}
				if (score.size() > 0) {
					IRScoreData sc = null;
					for (IRScoreData s : score) {
						if (sc == null || s.getClear() > sc.getClear()) {
							sc = s;
						}
					}
					result.put(hash, sc);
				} else {
					result.put(hash, null);
				}
			}
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("スコア" + playername + "取得時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	public void setScoreData(String playername, IRScoreData score) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			con.setAutoCommit(false);
			String sql = "delete from score where hash = ?";
			qr.update(con, sql, score.getHash());

			sql = "insert into score " + "(hash, clear, perfect, great, good, bad, poor, totalnotes, maxcombo, "
					+ "minbp, playcount, clearcount, failcount, rank, rate, clear_db, "
					+ "op_history, scorehash, ghost, clear_sd, clear_ex, op_best, rseed, " + "complete)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			qr.update(con, sql, score.getHash(), score.getClear(), score.getPg(), score.getGr(), score.getGd(),
					score.getBd(), score.getPr(), score.getNotes(), score.getCombo(), score.getMinbp(),
					score.getPlaycount(), score.getClearcount(), score.getPlaycount() - score.getClearcount(),
					score.getRank(), 0, 0, 0, "", "", 0, score.getExclear(), score.getOption(), 0, 0);
			con.commit();
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("スコア" + playername + "更新時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void setScoreData(String playername, Map<String, Map<String, Object>> map) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			con.setAutoCommit(false);
			for (String hash : map.keySet()) {
				Map<String, Object> values = map.get(hash);
				String vs = "";
				for (String key : values.keySet()) {
					vs += key + " = " + values.get(key) + ",";
				}
				if (vs.length() > 0) {
					vs = vs.substring(0, vs.length() - 1) + " ";
					qr.update(con, "UPDATE score SET " + vs + "WHERE hash = '" + hash + "'");
				}
			}
			con.commit();
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("スコア" + playername + "更新時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * ライバルスコアデータを取得する
	 * 
	 * @param hashes
	 *            スコアを取得する楽曲のhash
	 * @return <ハッシュ, スコアデータ>のマップ
	 */
	public Map<String, IRScoreData> getRivalScoreDatas(String rivalname, String[] hashes) {
		Map<String, IRScoreData> result = new HashMap<String, IRScoreData>();
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + rivalpath + rivalname + ".db");
			ResultSetHandler<List<IRScoreData>> rh = new BeanListHandler<IRScoreData>(IRScoreData.class);
			for (String hash : hashes) {
				List<IRScoreData> score = (List<IRScoreData>) qr.query(con,
						"SELECT r_clear as clear, CAST((r_perfect * 2 + r_great) * 9 / (r_totalnotes * 2) as int) as rank, "
								+ "r_perfect as pg,  r_great as gr, r_minbp as minbp FROM rival WHERE hash = '" + hash
								+ "'", rh);
				if (score.size() > 0) {
					result.put(hash, score.get(0));
				} else {
					result.put(hash, null);
				}
			}
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("ライバルスコア" + rivalname + "取得時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	/**
	 * プレイヤーデータを取得する
	 * 
	 * @return プレイヤーデータ
	 */
	public PlayerData getPlayerDatas(String playername) {
		PlayerData result = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			ResultSetHandler<List<PlayerData>> rh = new BeanListHandler<PlayerData>(PlayerData.class);
			List<PlayerData> pd = qr.query(conn, "SELECT * FROM player;", rh);
			if (pd.size() > 0) {
				result = pd.get(0);
			}
			conn.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("プレイヤーデータ" + playername + "取得時の例外:" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	public void setPlayerData(String playername, PlayerData pd) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + rootpath + playerpath + playername + ".db");
			con.setAutoCommit(false);
			qr.update(con, "UPDATE player SET " + "playcount = ? , clear = ? , fail = ?, perfect = ?, " +
					"great = ?, good = ?, bad = ?, poor = ?, playtime = ? WHERE id = ?", pd.getPlaycount(), pd.getClear(), pd.getFail(),
					pd.getPerfect(), pd.getGreat(), pd.getGood(), pd.getBad(), pd.getPoor(), pd.getPlaytime(), playername);
			con.commit();
			con.close();
		} catch (Exception e) {
			Logger.getGlobal().severe("スコア" + playername + "更新時の例外:" + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
