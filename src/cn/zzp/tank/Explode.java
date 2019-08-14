package cn.zzp.tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Explode extends GameObject {
	private static BufferedImage images[];
	static {
		images = new BufferedImage[16];
		for (int i = 0; i < images.length; i++) {
			try {
				images[i] = ImageIO.read(Explode.class.getClassLoader().getResourceAsStream("images/e" +(i+1)+ ".gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean die;
	private int step;
	TankClient tc;

	public boolean isDie() {
		return die;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public Explode(int x, int y, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.tc = tc;
		tc.objects.add(this);
	}

	@Override
	public void paint(Graphics g) {
		if (step==images.length) {
			die=true;
			return;
		}
		g.drawImage(images[step], x-images[0].getWidth()/2, y-images[0].getHeight()/2,null);
		step++;
	}
}
