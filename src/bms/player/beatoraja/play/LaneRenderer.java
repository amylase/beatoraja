package bms.player.beatoraja.play;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import bms.model.*;
import bms.player.beatoraja.Config;
import bms.player.beatoraja.PlayerResource;
import bms.player.beatoraja.input.BMSPlayerInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * レーン描画用クラス
 * 
 * @author exch
 */
public class LaneRenderer {

	/**
	 * レーンカバーの量
	 */
	private float lanecover = 0.2f;
	/**
	 * レーンカバーを表示するかどうか
	 */
	private boolean enableLanecover = true;

	private long lanecovertiming;
	/**
	 * リフトの量
	 */
	private float lift = 0.05f;
	/**
	 * リフトを使用するかどうか
	 */
	private boolean enableLift = true;

	private float hispeed = 1.0f;

	private int gvalue;

	private int fixhispeed;
	private float basehispeed;

	private BMSModel model;

	private int pos;

	private BMSPlayer main;

	private SpriteBatch sprite;
	private PlaySkin skin;

	private Config config;
	private int auto;

	Color[] lanebg = { new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK, new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK,
			new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK, new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK,
			new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK, new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK,
			new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK, new Color(0.05f, 0.05f, 0.05f, 1), Color.BLACK };

	private boolean hschanged;
	private long startpressedtime;
	private boolean startpressed;
	private boolean cursorpressed;

	public LaneRenderer(BMSPlayer main, SpriteBatch sprite, PlaySkin skin, PlayerResource resource, BMSModel model) {
		this.main = main;
		this.sprite = sprite;
		this.skin = skin;
		this.config = resource.getConfig();
		auto = resource.getAutoplay();
		this.enableLanecover = config.isEnablelanecover();
		this.enableLift = config.isEnablelift();
		this.lift = config.getLift();
		this.fixhispeed = config.getFixhispeed();
		this.gvalue = config.getGreenvalue();
		this.model = model;
		if (model.getUseKeys() == 9) {
			lanebg = new Color[] { new Color(0.1f, 0.1f, 0.1f, 1), new Color(0.1f, 0.1f, 0.0f, 1),
					new Color(0.0f, 0.1f, 0.0f, 1), new Color(0.0f, 0.0f, 0.1f, 1), new Color(0.1f, 0.0f, 0.0f, 1),
					new Color(0.0f, 0.0f, 0.1f, 1), new Color(0.0f, 0.1f, 0.0f, 1), new Color(0.1f, 0.1f, 0.0f, 1),
					new Color(0.1f, 0.1f, 0.1f, 1), Color.BLACK, new Color(0.1f, 0.1f, 0.1f, 1), Color.BLACK,
					new Color(0.1f, 0.1f, 0.1f, 1), Color.BLACK, new Color(0.1f, 0.1f, 0.1f, 1), Color.BLACK };
		}
		hispeed = config.getHispeed();
		switch (config.getFixhispeed()) {
		case Config.FIX_HISPEED_OFF:
			break;
		case Config.FIX_HISPEED_STARTBPM:
			basebpm = model.getBpm();
			break;
		case Config.FIX_HISPEED_MAXBPM:
			basebpm = model.getMaxBPM();
			break;
		case Config.FIX_HISPEED_MAINBPM:
			Map<Double, Integer> m = new HashMap<Double, Integer>();
			for (TimeLine tl : model.getAllTimeLines()) {
				Integer count = m.get(tl.getBPM());
				if (count == null) {
					count = 0;
				}
				m.put(tl.getBPM(), count + tl.getTotalNotes());
			}
			int maxcount = 0;
			for (double bpm : m.keySet()) {
				if (m.get(bpm) > maxcount) {
					maxcount = m.get(bpm);
					basebpm = bpm;
				}
			}
			break;
		}
		this.setLanecover(config.getLanecover());
		if (this.fixhispeed != Config.FIX_HISPEED_OFF) {
			basehispeed = hispeed;
		}
	}

	public int getFixHispeed() {
		return fixhispeed;
	}

	public float getHispeed() {
		return hispeed;
	}

	public int getGreenValue() {
		return gvalue;
	}

	public float getLaneCoverRegion() {
		return lanecover;
	}

	public boolean isEnableLift() {
		return enableLift;
	}

	public float getLiftRegion() {
		return lift;
	}

	public float getLanecover() {
		return lanecover;
	}

	public void setLanecover(float lanecover) {
		this.lanecover = lanecover;
		if (this.fixhispeed != Config.FIX_HISPEED_OFF) {
			hispeed = (float) ((2400f / (basebpm / 100) / gvalue) * 0.6 * (1 - (enableLanecover ? lanecover : 0)));
		}
	}

	public void setEnableLanecover(boolean b) {
		enableLanecover = b;
	}

	public boolean isEnableLanecover() {
		return enableLanecover;
	}

	public void changeHispeed(boolean b) {
		float f = 0;
		if (this.fixhispeed != Config.FIX_HISPEED_OFF) {
			f = basehispeed * 0.25f * (b ? 1 : -1);
		} else {
			f = 0.125f * (b ? 1 : -1);
		}
		if (hispeed + f > 0 && hispeed + f < 20) {
			hispeed += f;
		}
	}

	private double basebpm;
	private double nowbpm;

	public void drawLane(ShapeRenderer shape, BitmapFont font, BMSModel model, TimeLine[] timelines, long starttime,
			long time) {
		time += config.getJudgetiming();
		JudgeManager judge = main.getJudgeManager();
		final Rectangle[] laneregion = skin.getLaneregion();
		final Rectangle[] playerr = skin.getLaneGroupRegion();
		double bpm = model.getBpm();
		double nbpm = bpm;
		for (TimeLine t : timelines) {
			if (t.getTime() > time) {
				break;
			}
			if (t.getBPM() > 0) {
				bpm = t.getBPM();
			}
			nbpm = t.getBPM();
		}
		nowbpm = nbpm;
		int region = (int) (240000 / nbpm / hispeed);
		// double sect = (bpm / 60) * 4 * 1000;
		float hu = laneregion[0].y + laneregion[0].height;
		float hl = laneregion[0].y;

		// リフト描画
		if (enableLift) {
			for (int p = 0; p < playerr.length; p++) {
				sprite.begin();
				sprite.draw(skin.getLanecover(), playerr[p].x, hl, playerr[p].width, (hu - hl) * lift);
				// 緑数字、白数字描画
				if (main.getBMSPlayerInputProcessor().startPressed()) {
					font.setColor(Color.WHITE);
					font.draw(sprite, String.format("%5d", Math.round(lift * 1000)),
							playerr[p].x + playerr[p].width * 0.25f, hl + (hu - hl) * lift);
					font.setColor(Color.GREEN);
					font.draw(sprite,
							String.format("%5d", Math.round(region * 0.6 * (1 - (enableLanecover ? lanecover : 0)))),
							playerr[p].x + playerr[p].width * 0.75f, hl + (hu - hl) * lift);
				}
				sprite.end();
			}
			hl = hl + (hu - hl) * lift;
		}
		// 判定ライン描画
		for (int p = 0; p < playerr.length; p++) {
			shape.begin(ShapeType.Filled);
			shape.setColor(Color.RED);
			shape.rect(playerr[p].x, hl - 3, playerr[p].width, 6);

			shape.end();

		}

		boolean[] keystate = main.getBMSPlayerInputProcessor().getKeystate();
		for (int lane = 0; lane < laneregion.length; lane++) {
			float x = laneregion[lane].x;
			float dx = laneregion[lane].width;
			shape.begin(ShapeType.Filled);
			shape.setColor(lanebg[lane]);
			shape.rect(x, hl, dx, hu - hl);
			shape.end();
			// キービーム描画
			sprite.begin();
			if (model.getUseKeys() == 9) {
				if (keystate[lane]) {
					if (lane % 2 == 0) {
						sprite.draw(skin.getKeybeam()[0], x, hl, dx, hu - hl);
					} else {
						sprite.draw(skin.getKeybeam()[1], x, hl, dx, hu - hl);
					}
				}
			} else {
				int key = (model.getUseKeys() > 9 && lane >= 8 ? lane + 1 : lane);
				if (keystate[key] || (key == 7 && keystate[8]) || (key == 16 && keystate[17])) {
					if (lane == 7) {
						sprite.draw(skin.getKeybeam()[2], x, hl, dx, hu - hl);
					} else if (lane % 2 == 0) {
						sprite.draw(skin.getKeybeam()[0], x, hl, dx, hu - hl);
					} else {
						sprite.draw(skin.getKeybeam()[1], x, hl, dx, hu - hl);
					}
				}
			}
			sprite.end();

			shape.begin(ShapeType.Line);
			shape.setColor(Color.GRAY);
			shape.rect(x, hl, dx, hu - hl);
			shape.end();
		}

		// 各種コントロール入力判定
		// TODO ここで各種コントロール入力判定をやるべきではないかも
		BMSPlayerInputProcessor input = main.getBMSPlayerInputProcessor();
		if (input.getCursorState()[0]) {
			if (!cursorpressed) {
				float f = lanecover;
				f = f - 0.01f;
				if (f < 0) {
					f = 0;
				}
				this.setLanecover(f);
				cursorpressed = true;
			}
		} else if (input.getCursorState()[1]) {
			if (!cursorpressed) {
				float f = lanecover;
				f = f + 0.01f;
				if (f > 1) {
					f = 1;
				}
				this.setLanecover(f);
				cursorpressed = true;
			}
		} else {
			cursorpressed = false;
		}
		if (input.startPressed()) {
			if (auto == 0) {
				// change hi speed by START + Keys
				boolean[] key = input.getKeystate();
				if (key[0] || key[2] || key[4] || key[6]) {
					if (!hschanged) {
						changeHispeed(false);
						hschanged = true;
					}
				} else if (key[1] || key[3] || key[5]) {
					if (!hschanged) {
						changeHispeed(true);
						hschanged = true;
					}
				} else {
					hschanged = false;
				}

				// move lane cover by START + Scratch
				if (keystate[7] | keystate[8]) {
					long l = System.currentTimeMillis();
					if (l - lanecovertiming > 50) {
						float f = lanecover;
						f = f + (keystate[7] ? 0.001f : -0.001f);
						if (f > 1) {
							f = 1;
						}
						if (f < 0) {
							f = 0;
						}
						this.setLanecover(f);
						lanecovertiming = l;
					}
				}
			}
			// show-hide lane cover by double-press START
			if (!startpressed) {
				long stime = System.currentTimeMillis();
				if (stime < startpressedtime + 500) {
					setEnableLanecover(!isEnableLanecover());
					startpressedtime = 0;
				} else {
					startpressedtime = stime;
				}
			}
			startpressed = true;
		} else {
			startpressed = false;
		}

		float y = hl;

		// long ltime = 0;
		// float yy = 0;

		// 判定エリア表示
		if (config.isShowjudgearea()) {
			Gdx.gl.glEnable(GL11.GL_BLEND);
			Gdx.gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			shape.begin(ShapeType.Filled);
			final Color[] color = { Color.valueOf("0000ff20"), Color.valueOf("00ff0020"), Color.valueOf("ffff0020"),
					Color.valueOf("ff800020"), Color.valueOf("00000000"), Color.valueOf("ff000020") };
			int[] judgetime = judge.getJudgeTimeRegion();
			for (int i = pos; i < timelines.length && y <= hu; i++) {
				TimeLine tl = timelines[i];
				if (tl.getTime() >= time) {
					float rate = (float) (timelines[i].getSection() - (i > 0 ? timelines[i - 1].getSection() : 0))
							/ (timelines[i].getTime()
									- (i > 0 ? timelines[i - 1].getTime() + timelines[i - 1].getStop() : 0))
							* (hu - hl) * hispeed;
					for (int j = color.length - 1; j >= 0; j--) {
						shape.setColor(color[j]);
						int nj = j > 0 ? judgetime[j - 1] : 0;
						for (int p = 0; p < playerr.length; p++) {
							shape.rect(playerr[p].x, hl + nj * rate, playerr[p].width, (judgetime[j] - nj) * rate);
						}
					}
					break;
				}
			}
			shape.end();
			Gdx.gl.glDisable(GL11.GL_BLEND);
		}

		for (int i = pos; i < timelines.length && y <= hu; i++) {
			TimeLine tl = timelines[i];
			if (tl.getTime() >= time) {
				if (nbpm > 0) {
					if ((i > 0 && timelines[i - 1].getTime() + timelines[i - 1].getStop() > time)) {
						y += (float) (timelines[i].getSection() - timelines[i - 1].getSection()) * (hu - hl) * hispeed;
					} else {
						y += (float) (timelines[i].getSection() - (i > 0 ? timelines[i - 1].getSection() : 0))
								* (timelines[i].getTime() - time)
								/ (timelines[i].getTime()
										- (i > 0 ? timelines[i - 1].getTime() + timelines[i - 1].getStop() : 0))
								* (hu - hl) * hispeed;
					}
					// if(y < 0) {
					// System.out.println(" y : " + y + " line : " + (i > 0 ?
					// timelines[i]
					// .getTime() : 0) + " time : " + time + " stop : " + (i > 0
					// ? timelines[i - 1]
					// .getStop() : 0));
					// }
				}
				// ltime = tl.getTime() - time;
				// yy = y - hl;
				for (int p = 0; p < playerr.length; p++) {
					if (config.isBpmguide() && tl.getBPM() != nbpm) {
						// BPMガイド描画
						shape.begin(ShapeType.Line);
						shape.setColor(Color.valueOf("00c000"));
						shape.line(playerr[p].x, y + 2, playerr[p].x + playerr[p].width, y + 2);
						shape.line(playerr[p].x, y, playerr[p].x + playerr[p].width, y);
						shape.line(playerr[p].x, y - 2, playerr[p].x + playerr[p].width, y - 2);
						shape.end();
						sprite.begin();
						font.setColor(Color.valueOf("00c000"));
						font.draw(sprite, "BPM" + ((int) tl.getBPM()), playerr[p].x + playerr[p].width / 2, y + 20);
						sprite.end();
					}
					// 小節線描画
					if (tl.getSectionLine()) {
						shape.begin(ShapeType.Line);
						shape.setColor(Color.GRAY);
						shape.line(playerr[p].x, y, playerr[p].x + playerr[p].width, y);
						shape.end();
					}
				}
				nbpm = tl.getBPM();
			} else if (pos == i - 1) {
				boolean b = true;
				for (int lane = 0; lane < laneregion.length; lane++) {
					Note note = tl.getNote(model.getUseKeys() == 9 && lane >= 5 ? lane + 5
							: (model.getUseKeys() > 9 && lane >= 8 ? lane + 1 : lane));
					if (note != null && note instanceof LongNote && ((LongNote) note).getEnd().getTime() >= time) {
						b = false;
						break;
					}
				}
				if (b) {
					pos = i;
				}
			}
			// ノート描画
			sprite.begin();
			for (int lane = 0; lane < laneregion.length; lane++) {
				Note note = tl.getNote(model.getUseKeys() == 9 && lane >= 5 ? lane + 5
						: (model.getUseKeys() > 9 && lane >= 8 ? lane + 1 : lane));
				if (note != null) {
					float dy = 1;
					if (note instanceof LongNote) {
						if (((LongNote) note).getStart() == tl && ((LongNote) note).getEnd().getTime() >= time) {
							// if (((LongNote) note).getEnd() == null) {
							// Logger.getGlobal().warning(
							// "LN終端がなく、モデルが正常に表示されません。LN開始時間:"
							// + ((LongNote) note)
							// .getStart()
							// .getTime());
							// } else {
							dy = 0;
							for (int j = 0; timelines[i + j] != ((LongNote) note).getEnd(); j++) {
								if (timelines[i + j + 1].getTime() >= time) {
									if (timelines[i + j].getTime() + timelines[i + j].getStop() > time) {
										dy += (float) (timelines[i + j + 1].getSection()
												- timelines[i + j].getSection()) * (hu - hl) * hispeed;
									} else {
										dy += (timelines[i + j + 1].getSection() - timelines[i + j].getSection())
												* (timelines[i + j + 1].getTime() - time)
												/ (timelines[i + j + 1].getTime() - timelines[i + j].getTime())
												* (hu - hl) * hispeed;
									}
								}
							}
							// System.out.println(dy);
						} else {
							dy = 0;
						}
					} else {
						if (timelines[i].getTime() < time) {
							dy = 0;
						}
					}
					if (dy > 0) {
						this.drawNote(laneregion[lane].x, y + dy, laneregion[lane].width, dy, 2.0f, lane, note);
					}
				}
			}
			sprite.end();

		}

		// System.out.println("time :" + ltime + " y :" + yy + " real time : "
		// + (ltime * (hu - hl) / yy));

		sprite.begin();

		long[] bomb = judge.getBomb();
		// sprite.enableBlending();
		sprite.setBlendFunction(GL11.GL_ONE, GL11.GL_ONE);
		for (int lane = 0; lane < laneregion.length; lane++) {
			// ボム描画
			if (time >= bomb[lane]) {
				sprite.draw(
						skin.getBomb()[judge.getProcessingLongNotes()[lane] != null ? 2 : 0]
								.getKeyFrame((time - bomb[lane]) / 1000f),
						laneregion[lane].x + laneregion[lane].width / 2 - 110, hl - 155, 260, 270);
			}
		}
		sprite.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		// sprite.disableBlending();

		final int[] judgenow = judge.getJudgeNow();
		final int[] judgenowt = judge.getJudgeTime();
		final int[] combo = judge.getJudgeCombo();
		// 判定文字描画
		for (int jr = 0; jr < skin.getJudgeregion().length; jr++) {
			if (judgenow[jr] > 0 && time < judgenowt[jr] + 500) {
				final float f = 1.5f;
				Sprite s = skin.getJudge()[(judgenow[jr] == 6 ? 5 : judgenow[jr]) - 1];
				int w = s.getRegionWidth();
				if (judgenow[jr] < 4) {
					w += 20;
					int count = 0;
					for (int i = combo[jr];; count++) {
						w += 25;
						if (i < 10) {
							break;
						} else {
							i /= 10;
						}
					}
					for (int i = combo[jr], j = 0;; j++) {
						sprite.draw(skin.getJudgenum()[judgenow[jr] - 1][i % 10],
								skin.getJudgeregion()[jr].x + skin.getJudgeregion()[jr].width / 2
										+ (-w / 2 + s.getRegionWidth() + 20 + (count - j) * 25) * f,
								hl + 100, 25 * f, 52 * f);
						if (i < 10) {
							break;
						} else {
							i /= 10;
						}
					}
				}
				sprite.draw(s, skin.getJudgeregion()[jr].x + skin.getJudgeregion()[jr].width / 2 - w * f / 2, hl + 100,
						s.getRegionWidth() * f, s.getRegionHeight() * f);
				// FAST, SLOW描画
				if (config.getJudgedetail() == 1) {
					if (judgenow[jr] > 1) {

						font.setColor(judge.getRecentJudgeTiming() >= 0 ? Color.CYAN : Color.RED);
						font.draw(sprite, judge.getRecentJudgeTiming() >= 0 ? "FAST" : "SLOW",
								skin.getJudgeregion()[jr].x + skin.getJudgeregion()[jr].width / 2, hl + 180);
					}

				} else if (config.getJudgedetail() == 2) {
					if (judgenow[jr] > 0) {

						if(judgenow[jr] == 1) {
							font.setColor(judge.getRecentJudgeTiming() >= 0 ? Color.SKY : Color.PINK);														
						} else {
							font.setColor(judge.getRecentJudgeTiming() >= 0 ? Color.BLUE : Color.RED);							
						}
						font.draw(sprite,
								(judge.getRecentJudgeTiming() >= 0 ? "+" : "") + judge.getRecentJudgeTiming() + " ms",
								skin.getJudgeregion()[jr].x + skin.getJudgeregion()[jr].width / 2, hl + 180);
					}

				}
			}
		}

		// レーンカバー描画
		for (int p = 0; p < playerr.length; p++) {
			if (enableLanecover) {
				sprite.draw(skin.getLanecover(), playerr[p].x, hl + (hu - hl) * (1 - lanecover), playerr[p].width,
						(hu - hl));
			}
			// 緑数字、白数字描画
			if (main.getBMSPlayerInputProcessor().startPressed()) {
				font.setColor(Color.WHITE);
				font.draw(sprite, String.format("%5d", Math.round(lanecover * 1000)),
						playerr[p].x + playerr[p].width * 0.25f,
						hl + (hu - hl) * (enableLanecover ? (1 - lanecover) : 1));
				font.setColor(Color.GREEN);
				font.draw(sprite,
						String.format("%5d", Math.round(region * 0.6 * (1 - (enableLanecover ? lanecover : 0)))),
						playerr[p].x + playerr[p].width * 0.75f,
						hl + (hu - hl) * (enableLanecover ? (1 - lanecover) : 1));
			}
		}
		sprite.end();

	}

	public double getNowBPM() {
		return nowbpm;
	}

	private void drawNote(float x, float y, float width, float height, float scale, int lane, Note note) {
		if (note instanceof NormalNote) {
			Sprite s = skin.getNote()[lane];
			if (config.isMarkprocessednote() && note.getState() != 0) {
				// TODO 処理済みノートの描画
				s.setAlpha(0.3f);
				sprite.draw(s, x, y - s.getHeight() * scale / 2, width, s.getHeight() * scale);
				s.setAlpha(1.0f);
			} else {
				sprite.draw(s, x, y - s.getHeight() * scale / 2, width, s.getHeight() * scale);
			}
		}
		if (note instanceof LongNote) {
			if (model.getLntype() == 2) {
				// HCN
				if (y - height < skin.getLaneregion()[lane].y) {
					height = y - skin.getLaneregion()[lane].y;
				}
				JudgeManager judge = main.getJudgeManager();

				boolean[] inclease = judge.getHellChargeJudges();
				if (main.getJudgeManager().getProcessingLongNotes()[lane] == note) {
					sprite.draw(skin.getLongnote()[6][lane], x, y - height - 2, width, height + 4);
				} else if (note.getState() > 2 && judge.getPassingLongNotes()[lane] == note) {
					sprite.draw(skin.getLongnote()[inclease[lane] ? 8 : 9][lane], x, y - height - 2, width, height + 4);
				} else {
					sprite.draw(skin.getLongnote()[7][lane], x, y - height - 2, width, height + 4);
				}
				Sprite ls = skin.getLongnote()[4][lane];
				sprite.draw(ls, x, y - ls.getHeight() * scale / 2, width, ls.getHeight() * scale);
				Sprite le = skin.getLongnote()[5][lane];
				sprite.draw(le, x, y - height - le.getHeight() * scale / 2, width, le.getHeight() * scale);
			}
			if (model.getLntype() == 1) {
				// CN
				if (y - height < skin.getLaneregion()[lane].y) {
					height = y - skin.getLaneregion()[lane].y;
				}
				if (main.getJudgeManager().getProcessingLongNotes()[lane] == note) {
					sprite.draw(skin.getLongnote()[2][lane], x, y - height - 2, width, height + 4);
				} else {
					sprite.draw(skin.getLongnote()[3][lane], x, y - height - 2, width, height + 4);
				}
				Sprite ls = skin.getLongnote()[0][lane];
				sprite.draw(ls, x, y - ls.getHeight() * scale / 2, width, ls.getHeight() * scale);
				Sprite le = skin.getLongnote()[1][lane];
				sprite.draw(le, x, y - height - le.getHeight() * scale / 2, width, le.getHeight() * scale);
			}
			if (model.getLntype() == 0) {
				// LN
				if (y - height < skin.getLaneregion()[lane].y) {
					height = y - skin.getLaneregion()[lane].y;
				}
				if (main.getJudgeManager().getProcessingLongNotes()[lane] == note) {
					sprite.draw(skin.getLongnote()[2][lane], x, y - height - 2, width, height + 4);
				} else {
					sprite.draw(skin.getLongnote()[3][lane], x, y - height - 2, width, height + 4);
				}
				Sprite le = skin.getLongnote()[1][lane];
				sprite.draw(le, x, y - height - le.getHeight() * scale / 2, width, le.getHeight() * scale);
			}
		}
		if (note instanceof MineNote) {
			Sprite s = skin.getMinenote()[lane];
			sprite.draw(s, x, y - s.getHeight() * scale / 2, width, s.getHeight() * scale);
		}
	}
}
