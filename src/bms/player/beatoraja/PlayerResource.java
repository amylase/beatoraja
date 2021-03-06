package bms.player.beatoraja;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bms.model.*;
import bms.player.beatoraja.gauge.GrooveGauge;
import bms.player.beatoraja.play.audio.AudioProcessor;
import bms.player.beatoraja.play.audio.SoundProcessor;
import bms.player.beatoraja.play.bga.BGAProcessor;

/**
 * プレイヤーのコンポーネント間でデータをやり取りするためのクラス
 * 
 * @author exch
 */
public class PlayerResource {
	private BMSModel model;
	private Config config;
	private int auto;
	
	private int bgashow;
	/**
	 * BMSの音源リソース
	 */
	private AudioProcessor audio;
	/**
	 * BMSのBGAリソース
	 */
	private BGAProcessor bga;
	/**
	 * BMSのリソースの読み込みが完了したかどうか
	 */
	private boolean finished = false;
	
	/**
	 * スコア
	 */
	private IRScoreData score;
	/**
	 * スコア更新するかどうか
	 */
	private boolean updateScore = true;
	private GrooveGauge grooveGauge;
	/**
	 * ゲージの遷移ログ
	 */
	private List<Float> gauge;

	private List<List<Float>> coursegauge = new ArrayList();

	/**
	 * コースタイトル
	 */
	private String coursetitle;
	/**
	 * コースのBMSモデル
	 */
	private BMSModel[] course;
	/**
	 * コース何曲目
	 */
	private int courseindex;

	private ReplayData replay;

	private List<ReplayData> courseReplay = new ArrayList<ReplayData>();
	/**
	 * コーススコア
	 */
	private IRScoreData cscore;

	private int combo;

	private int maxcombo;

	public PlayerResource(Config config) {
		this.config = config;
	}
	
	public void clear() {
		coursetitle = null;
		course = null;
		courseindex = 0;
		cscore = null;
		score = null;
		gauge = null;
		courseReplay.clear();
		coursegauge.clear();
		combo = 0;
		maxcombo = 0;
	}

	public boolean setBMSFile(final File f, final Config config, int autoplay) {
		this.config = config;
		this.auto = autoplay;
		replay = new ReplayData();
		String bmspath = model != null ? model.getPath() : null;
		model = loadBMSModel(f);
		if(model.getAllTimeLines().length == 0) {
			return false;
		}
		if(bmspath == null || !f.getAbsolutePath().equals(bmspath) || bgashow != config.getBga()) {
			// 前回と違うbmsファイルを読み込んだ場合、BGAオプション変更時はリソースのロード
			// 同フォルダの違うbmsファイルでも、WAV/,BMP定義が違う可能性があるのでロード
			this.bgashow = config.getBga();
			this.finished = false;
			if(audio != null) {
				audio.dispose();
			}
			audio = new SoundProcessor();
			if(bga != null) {
				bga.dispose();
			}
			bga = new BGAProcessor(config);
			Thread medialoader = new Thread() {
				@Override
				public void run() {
					try {
						if (config.getBga() == Config.BGA_ON
								|| (config.getBga() == Config.BGA_AUTO && (auto != 0))) {
							bga.setModel(model, f.getPath());
						}
						audio.setModel(model, f.getPath());
					} catch (Exception e) {
						Logger.getGlobal()
								.severe(e.getClass().getName() + " : "
										+ e.getMessage());
						e.printStackTrace();
					} catch (Error e) {
						Logger.getGlobal()
								.severe(e.getClass().getName() + " : "
										+ e.getMessage());
					} finally {
						finished = true;
					}
				}
			};
			medialoader.start();				
		}
		return true;
	}
	
	private BMSModel loadBMSModel(File f) {
		BMSModel model;
		if (f.getPath().toLowerCase().endsWith(".bmson")) {
			BMSONDecoder decoder = new BMSONDecoder(
					BMSModel.LNTYPE_CHARGENOTE);
			model = decoder.decode(f);
			if(model.getTotal() <= 0.0) {
				model.setTotal(100.0);
			}
			int totalnotes = model.getTotalNotes();
			model.setTotal(model.getTotal() / 100.0 * 7.605 * totalnotes / (0.01 * totalnotes + 6.5));
		} else {
			BMSDecoder decoder = new BMSDecoder(BMSModel.LNTYPE_CHARGENOTE);
			model = decoder.decode(f);
			// JUDGERANKをbmson互換に変換
			if(model.getJudgerank() < 0 || model.getJudgerank() > 2) {
				model.setJudgerank(100);
			} else {
				final int[] judgetable = {40, 70 ,90};
				model.setJudgerank(judgetable[model.getJudgerank()]);
			}
			// TOTAL未定義の場合
			if(model.getTotal() <= 0.0) {
				int totalnotes = model.getTotalNotes();
				model.setTotal(7.605 * totalnotes / (0.01 * totalnotes + 6.5));
			}
		}

		return model;
	}

	public BMSModel getBMSModel() {
		return model;
	}

	public int getAutoplay() {
		return auto;
	}

	public Config getConfig() {
		return config;
	}

	public AudioProcessor getAudioProcessor() {
		return audio;
	}

	public BGAProcessor getBGAManager() {
		return bga;
	}

	public boolean mediaLoadFinished() {
		return finished;
	}

	public IRScoreData getScoreData() {
		return score;
	}

	public void setScoreData(IRScoreData score) {
		this.score = score;
	}

	public void setCourseBMSFiles(File[] files) {
		List<BMSModel> models = new ArrayList();
		for (File f : files) {
			models.add(loadBMSModel(f));
		}
		course = models.toArray(new BMSModel[0]);
	}

	public BMSModel[] getCourseBMSModels() {
		return course;
	}

	public boolean nextCourse() {
		courseindex++;
		if (courseindex == course.length) {
			return false;
		} else {
			setBMSFile(new File(course[courseindex].getPath()), config, auto);
			return true;
		}
	}

	public void reloadBMSFile() {
		File f = new File(model.getPath());
		model = loadBMSModel(f);
		clear();
	}

	public List<Float> getGauge() {
		return gauge;
	}

	public void setGauge(List<Float> gauge) {
		this.gauge = gauge;
	}

	public GrooveGauge getGrooveGauge() {
		return grooveGauge;
	}

	public void setGrooveGauge(GrooveGauge grooveGauge) {
		this.grooveGauge = grooveGauge;
	}

	public ReplayData getReplayData() {
		return replay;
	}

	public void setReplayData(ReplayData replay) {
		this.replay = replay;
	}

	public IRScoreData getCourseScoreData() {
		return cscore;
	}

	public void setCourseScoreData(IRScoreData cscore) {
		this.cscore = cscore;
	}

	public boolean isUpdateScore() {
		return updateScore;
	}
	
	public void setUpdateScore(boolean b) {
		this.updateScore = b;
	}

	public String getCoursetitle() {
		return coursetitle;
	}

	public void setCoursetitle(String coursetitle) {
		this.coursetitle = coursetitle;
	}

	public ReplayData[] getCourseReplay() {
		return courseReplay.toArray(new ReplayData[0]);
	}

	public void addCourseReplay(ReplayData rd) {
		courseReplay.add(rd);
	}

	public List<List<Float>> getCourseGauge() {
		return coursegauge;
	}

	public void addCourseGauge(List<Float> gauge) {
		coursegauge.add(new ArrayList(gauge));
	}

	public int getCombo() {
		return combo;
	}

	public void setCombo(int combo) {
		this.combo = combo;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	public void setMaxcombo(int maxcombo) {
		this.maxcombo = maxcombo;
	}
}