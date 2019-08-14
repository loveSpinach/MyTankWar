package cn.zzp.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import cn.zzp.tank.Bullet;
import cn.zzp.tank.Tank;
import cn.zzp.tank.TankClient;

public class Bullet extends GameObject {
	public static int SPEED = 7;
	public static final int WIDTH =8, HEIGHT = 8;
	private Dir dir;
	private boolean good;
	TankClient tc;
	private boolean die;
	Image missileL=GameUtil.getImage("images/missileL.gif");
	Image missileR=GameUtil.getImage("images/missileR.gif");
	Image missileU=GameUtil.getImage("images/MissileU.gif");
	Image missileD=GameUtil.getImage("images/missileD.gif");
	
	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isDie() {
		return die;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	public Bullet(){
		
	}
	
	public Bullet(int x, int y, boolean good, Dir dir,TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this.tc=tc;
		 tc.bullets.add(this);
	}
	
	@Override
	public void paint(Graphics g) {
		if(good) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.red);
		}
		switch (dir) {
		case L:
			g.drawImage(missileL, x, y, Bullet.WIDTH, Bullet.HEIGHT, null, null);
			break;
		case R:
			g.drawImage(missileR, x, y, Bullet.WIDTH, Bullet.HEIGHT, null, null);
			break;
		case D:
			g.drawImage(missileD, x, y, Bullet.WIDTH, Bullet.HEIGHT, null, null);
			break;
		case U:
			g.drawImage(missileU, x, y, Bullet.WIDTH, Bullet.HEIGHT, null, null);
			break;
		}

		move();
		
	}

	private void move() {
		if(x<0||x>TankClient.GAME_W-Bullet.WIDTH ||y < 30||y > TankClient.GAME_H-Bullet.HEIGHT){
			setDie(true);
		}
		switch (dir) {
		case L:
			x -= SPEED;
			break;
		case U:
			y -= SPEED;
			break;
		case R:
			x += SPEED;
			break;
		case D:
			y += SPEED;
			break;
		}
		strike();
		
	}
	public void strike(){
		int centerX=this.x+Bullet.WIDTH/2;
		int centerY=this.y+Bullet.HEIGHT/2;
		for(Iterator<Tank>it=tc.enemies.iterator();it.hasNext();){
			Tank tank=it.next();
			if ((centerX>=tank.getX())&&(centerX<=tank.getX()+Tank.WIDTH)&&(centerY>=tank.getY())&&(centerY<=tank.getY()+Tank.HEIGHT)) {
				if (this.isGood()!=tank.isGood()) {
					tank.died();
					this.died();
					new Explode(tank.getX()+tank.WIDTH/2, tank.getY()+tank.HEIGHT, tc);
					new Audio("images/explode.wav").play();
					break;
				}
			}
		}
		if (GameUtil.bulletsHitWall(this)) {
			this.died();
		}
		
		
	}

	private void died() {
		this.die=true;
		
	}
}





