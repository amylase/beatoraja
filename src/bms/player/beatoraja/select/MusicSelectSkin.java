package bms.player.beatoraja.select;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import bms.player.beatoraja.skin.NumberResourceAccessor;
import bms.player.beatoraja.skin.Skin;
import bms.player.beatoraja.skin.SkinNumber;

import com.badlogic.gdx.math.Rectangle;

public class MusicSelectSkin extends Skin {

	/**
	 * 楽曲バー画像
	 */
	private Sprite[] bar = new Sprite[10];
	/**
	 * ランプ画像
	 */
	private Animation[] lamp = new Animation[11];

	private Rectangle seekRegion;

	public MusicSelectSkin() {
		List<SkinNumber> numbers = new ArrayList<SkinNumber>();

		Texture bart = new Texture("skin/songbar.png");
		for(int i = 0;i < bar.length;i++) {
			bar[i] = new Sprite(bart, 0, i * 30, 500, 30);
		}
		TextureRegion[][] lampt = TextureRegion.split(new Texture("skin/lamp.png"),15,30);
		for(int i = 0;i < lamp.length;i++) {
			lamp[i] = new Animation(2 / 60f, lampt[i]);
			lamp[i].setPlayMode(PlayMode.LOOP);
		}

		seekRegion = new Rectangle(1240, 90, 10, 540);
		
		// 数字
		Texture nt = new Texture("skin/number.png");
		TextureRegion[][] ntr = TextureRegion.split(nt, 24, 24);

		SkinNumber year = new SkinNumber(ntr[0], 0, 4, 1, NumberResourceAccessor.TIME_YEAR);
		year.setDestination(0, 1028, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(year);
		SkinNumber month = new SkinNumber(ntr[0], 0, 2, 1, NumberResourceAccessor.TIME_MONTH);
		month.setDestination(0, 1088, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(month);
		SkinNumber day = new SkinNumber(ntr[0], 0, 2, 1, NumberResourceAccessor.TIME_DAY);
		day.setDestination(0, 1130, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(day);
		SkinNumber hour = new SkinNumber(ntr[0], 0, 2, 1, NumberResourceAccessor.TIME_HOUR);
		hour.setDestination(0, 1178, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(hour);
		SkinNumber minute = new SkinNumber(ntr[0], 0, 2, 1, NumberResourceAccessor.TIME_MINUTE);
		minute.setDestination(0, 1214, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(minute);
		SkinNumber second = new SkinNumber(ntr[0], 0, 2, 1, NumberResourceAccessor.TIME_SECOND);
		second.setDestination(0, 1250, 2, 12, 12, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		numbers.add(second);

		this.setSkinNumbers(numbers.toArray(new SkinNumber[0]));
	}
	
	public Sprite[] getBar() {
		return bar;
	}

	public void setBar(Sprite[] bar) {
		this.bar = bar;
	}

	public Animation[] getLamp() {
		return lamp;
	}

	public void setLamp(Animation[] lamp) {
		this.lamp = lamp;
	}

	public Rectangle getSeekRegion() {
		return seekRegion;
	}

	public void setSeekRegion(Rectangle positionRegion) {
		this.seekRegion = positionRegion;
	}
}
