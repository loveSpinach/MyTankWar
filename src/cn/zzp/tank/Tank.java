	package cn.zzp.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;


public class Tank extends GameObject {
	public static final int WIDTH = 50,HEIGHT = 50;
	public static int SPEED = 5;
	TankClient tc;
	  Dir tankHitWallBeforeDir=null;
	  Dir tankHitTankBeforeDir=null;
	Image tank=GameUtil.getImage("images/tanker.jpg");
	Image tankleft=GameUtil.getImage("images/tankerleft.jpg");
	Image tankerright=GameUtil.getImage("images/tankerright.jpg");
	Image tankerdown=GameUtil.getImage("images/tankerdown.jpg");
	private int oldX,oldY;
	private Random r = new Random();
	private boolean good;
    private boolean moving=false;
	private Dir dir = Dir.D;
	private boolean die;

	public boolean isMoving() {
		return  moving;
	}

	public void setMoving(boolean moving) {
		this.moving =moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public Tank(int x, int y, boolean good, Dir dir,boolean  moving,TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this. moving= moving;
		this.tc=tc;
		oldX =this.x;
		oldY =this.y;
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

	@Override
	public void paint(Graphics g) {
		switch (dir) {
	case U:
		if(good) {
			g.drawImage(tank, x, y, WIDTH,HEIGHT, Color.blue, null);
		} else {
			g.drawImage(tank, x, y, WIDTH,HEIGHT, Color.RED, null);
		}
		break;
	case L:
		if(good) {
			g.drawImage(tankleft, x, y, WIDTH,HEIGHT, Color.blue, null);
		} else {
			g.drawImage(tankleft, x, y, WIDTH,HEIGHT, Color.RED, null);
		}
		break;
	case R:
		if(good) {
			g.drawImage(tankerright, x, y, WIDTH,HEIGHT, Color.blue, null);
		} else {
			g.drawImage(tankerright, x, y, WIDTH,HEIGHT, Color.RED, null);
		}
		break;
	case D:
		if(good) {
			g.drawImage(tankerdown, x, y, WIDTH,HEIGHT, Color.blue, null);
		} else {
			g.drawImage(tankerdown, x, y, WIDTH,HEIGHT, Color.RED, null);
		}
		break;
	}
		move();
	}

	private void move() {
		if ( !moving) return;
		oldX =x;
		oldY=y;
		tankHitTankBeforeDir=this.getDir();
		if(!good) randomDir();
		if(x<0 ) x = 0;
		if (x>TankClient.GAME_W-Tank.WIDTH) x = TankClient.GAME_W-Tank.WIDTH;
		if (y < 25) y = 25;
		if(y > TankClient.GAME_H-Tank.HEIGHT) y = TankClient.GAME_H-Tank.HEIGHT;
		if (!GameUtil.validateMove(this)) {
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
			tankHitWallBeforeDir=this.dir;
			
		}else {
			//System.out.println("ײǽ��");
			if (dir==tankHitWallBeforeDir) {
				return;
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
		}
		
		if (GameUtil.tankHit(this, tc)) {
			back();
		}
		
		
	}

	private void randomDir() {
		if(r.nextInt(100) < 95) return;
		Dir[] dirs = Dir.values();
		int i = r.nextInt(dirs.length);
		dir = dirs[i];
		tc.bullets.add(fire());
	}

	public Bullet fire() {
		return new Bullet(x+this.WIDTH/2-Bullet.WIDTH,y+this.HEIGHT/2-Bullet.HEIGHT,good,dir,tc);
		
		
	}

	public void died() {
		tc.enemies.remove(this);
		
	}
	public void back(){
		this.x=oldX;
		this.y=oldY;
	}
}
