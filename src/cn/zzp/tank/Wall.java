package cn.zzp.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class Wall extends Frame {
	TankClient tc;
	 public Wall(TankClient tc) {
	this.tc=tc;
	}

	@Override
	public void paint(Graphics g) {

		for (int i = 0; i < 2*TankClient.GAME_W/Tank.WIDTH; i++) {
			for (int j = 0; j < 2*TankClient.GAME_H/Tank.HEIGHT; j++) {
				g.setColor(Color.GRAY);
				g.drawRect(i*Tank.WIDTH/2, j*Tank.HEIGHT/2, Tank.WIDTH/2, Tank.HEIGHT/2);
			
				
				if (i==2*TankClient.GAME_W/Tank.WIDTH-2||i==2*TankClient.GAME_W/Tank.WIDTH-1||i==2*TankClient.GAME_W/Tank.WIDTH-3||i==1||i==0||i==2||i==TankClient.GAME_W/Tank.WIDTH+1||i==TankClient.GAME_W/Tank.WIDTH||i==TankClient.GAME_W/Tank.WIDTH-1||i==TankClient.GAME_W/Tank.WIDTH-2||j==TankClient.GAME_H/Tank.HEIGHT+1||j==TankClient.GAME_H/Tank.HEIGHT-2||j==TankClient.GAME_H/Tank.HEIGHT-1||j==TankClient.GAME_H/Tank.HEIGHT||j==0||j==3||j==1||j==2||j==2*TankClient.GAME_H/Tank.HEIGHT-3||j==2*TankClient.GAME_H/Tank.HEIGHT-1||j==2*TankClient.GAME_H/Tank.HEIGHT-2) {
					continue;
				}
				
				g.setColor(new Color(199,97,20));
				g.fillRect(i*Tank.WIDTH/2, j*Tank.HEIGHT/2, Tank.WIDTH/2, Tank.HEIGHT/2);
				g.setColor(new Color(180,82,45));
				g.drawRect(i*Tank.WIDTH/2, j*Tank.HEIGHT/2, Tank.WIDTH/2, Tank.HEIGHT/2);
			}
			
		}
		
	}
	

}
