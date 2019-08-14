package cn.zzp.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankClient extends Frame {
	public static final int GAME_W = 800, GAME_H = 600, GAME_S = 50;
	static int count = 0;
	Tank t = new Tank(GAME_W / 2 - Tank.WIDTH / 2, GAME_H / 2 - Tank.HEIGHT / 2, true, Dir.U,false, this);
	List<Tank> enemies = new ArrayList<Tank>();
	List<Bullet> bullets = new ArrayList<Bullet>();  
	List<GameObject>objects=new ArrayList<GameObject>();
	Image bg = GameUtil.getImage("images/bg1.jpg");
	Image gameover = GameUtil.getImage("images/gameover2.jpg");
	Image victory = GameUtil.getImage("images/victory.jpg");
	Wall wall = new Wall(this);
	public TankClient() {
		ConfigUtil.initConfig();
		enemies.add(t);
		// init enemies
		enemies.add(new Tank(0, 0, false, Dir.D, true,this));
		enemies.add(new Tank(0, GAME_H - Tank.HEIGHT, false, Dir.R,true, this));
		enemies.add(new Tank(GAME_W - Tank.WIDTH, 0, false, Dir.U,true, this));
		enemies.add(new Tank(GAME_W - Tank.WIDTH, GAME_H - Tank.HEIGHT, false, Dir.L,true, this));
		enemies.add(new Tank(GAME_W / 2 - Tank.WIDTH / 2, 0, false, Dir.D, true,this));
		enemies.add(new Tank(0, GAME_H / 2 - Tank.HEIGHT / 2, false, Dir.D,true, this));
		enemies.add(new Tank(GAME_W, GAME_H / 2 - Tank.HEIGHT / 2, false, Dir.D,true, this));
		enemies.add(new Tank(GAME_W / 2 - Tank.WIDTH / 2, GAME_H, false, Dir.U, true,this));

		this.setVisible(true);
		setLocation(100, 100);
		setSize(GAME_W, GAME_H);
		setResizable(false);
		setTitle("̹坦克大战");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		addKeyListener(new TankKeyListener());
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, GAME_W, GAME_H, null, null);
		Font f = new Font("黑体", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("tanks:" + enemies.size(), 60, 50);
		g.drawString("bullets:" + bullets.size(), 60, 70);
		g.drawString("explodes:" + objects
				.size(), 60, 90);
		wall.paint(g);
		g.setColor(Color.RED);
		if (!enemies.contains(t)) {
			g.drawImage(gameover, 0, 0, GAME_W, GAME_H, null);
			Font b = new Font("黑体", Font.BOLD, 40);
			g.setFont(b);
			g.setColor(Color.red);
			g.drawString("共击毁"+(8-enemies.size())+"敌军坦克", 200, 120);
			return;
		} else if (enemies.size() == 1) {
			g.drawImage(victory, 0, 0, GAME_W, GAME_H, null);
			Font b = new Font("黑体", Font.BOLD, 30);
			g.setFont(b);
			g.setColor(Color.BLUE);
			g.drawString("恭喜,胜利", 200, 450);
			g.drawString("共击击毁"+(8-enemies.size())+"敌军坦克", 200, 480);
		}
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.isDie()) {

				bullets.remove(i);
			} else {
				bullet.paint(g);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			Tank tank = enemies.get(i);
			if (tank.isDie()) {

				enemies.remove(i);

			} else {
				tank.paint(g);
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject o=objects.get(i);
				o.paint(g);
			
		}
	}

	/**
	 * ����˫���弼��ʵ�ֽ��治��˸  
	 */
	private Image offScreenImage = null;

	public void update(Graphics g) {  
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_W, GAME_H);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);

	}

	public static void main(String[] args) throws Exception {
		Frame f = new TankClient();

		while (true) {
			Thread.sleep(60);
			f.repaint();
		}
	}

	class TankKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
				t.setDir(Dir.U);
				t.setMoving(true);
				break;

			case KeyEvent.VK_RIGHT:
				t.setDir(Dir.R);
				t.setMoving(true);
				break;
			case KeyEvent.VK_DOWN:
				t.setDir(Dir.D);
				t.setMoving(true);
				break;
			case KeyEvent.VK_LEFT:
				t.setDir(Dir.L);
				t.setMoving(true);
				break;
			case KeyEvent.VK_CONTROL:
				t.fire();

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_LEFT:
				t.setMoving(false);
			}

		}
		
	}
	
	
}
