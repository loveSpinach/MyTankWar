 
package cn.zzp.tank;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil {
	static Image getImage(String path){
		URL u=GameUtil.class.getClassLoader().getResource(path);
		BufferedImage image=null;
		try {
			image=ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	/**
	 * 利用Rect.intersects(Rect)方法确定是否碰撞
	 * @param tank
	 * @return
	 */
	public static boolean validateMove (Tank tank){
		
		boolean isRight = false ;
		Rectangle tankRec = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT); 
		Rectangle wall1 = new Rectangle(3*Tank.WIDTH/2,4*Tank.HEIGHT/2,11*Tank.WIDTH/2,6*Tank.HEIGHT/2); 
		Rectangle wall2 = new Rectangle(18*Tank.WIDTH/2,4*Tank.HEIGHT/2,11*Tank.WIDTH/2,6*Tank.HEIGHT/2); 
		Rectangle wall3 = new Rectangle(3*Tank.WIDTH/2,14*Tank.HEIGHT/2,11*Tank.WIDTH/2,7*Tank.HEIGHT/2); 
		Rectangle wall4 = new Rectangle(18*Tank.WIDTH/2,14*Tank.HEIGHT/2,11*Tank.WIDTH/2,7*Tank.HEIGHT/2);
		if (tankRec.intersects(wall1)||tankRec.intersects(wall2)||tankRec.intersects(wall3)||tankRec.intersects(wall4)) {
			isRight = true;
		}
		return isRight ; 
	}
	public static boolean tankHit(Tank tank,TankClient tc){
		boolean isRight = false ;
		Rectangle tankRec = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT); 
		for(Tank tank1:tc.enemies){
				if (tank!=tank1&&tankRec.intersects(new Rectangle(tank1.getX(), tank1.getY(),Tank.WIDTH, Tank.HEIGHT))) {
					isRight = true;
				}
			
			
		}
		
		return isRight ; 
	}
	public static boolean bulletsHitWall(Bullet bullet){
		boolean isRight = false ;
		Rectangle bulletRec = new Rectangle(bullet.getX(),bullet.getY(),Bullet.WIDTH,Bullet.HEIGHT); 
		Rectangle wall1 = new Rectangle(3*Tank.WIDTH/2,4*Tank.HEIGHT/2,11*Tank.WIDTH/2,6*Tank.HEIGHT/2); 
		Rectangle wall2 = new Rectangle(18*Tank.WIDTH/2,4*Tank.HEIGHT/2,11*Tank.WIDTH/2,6*Tank.HEIGHT/2); 
		Rectangle wall3 = new Rectangle(3*Tank.WIDTH/2,14*Tank.HEIGHT/2,11*Tank.WIDTH/2,7*Tank.HEIGHT/2); 
		Rectangle wall4 = new Rectangle(18*Tank.WIDTH/2,14*Tank.HEIGHT/2,11*Tank.WIDTH/2,7*Tank.HEIGHT/2);
		if (bulletRec.intersects(wall1)||bulletRec.intersects(wall2)||bulletRec.intersects(wall3)||bulletRec.intersects(wall4)) {
			isRight = true;
		}
		return isRight ; 
	}



}
