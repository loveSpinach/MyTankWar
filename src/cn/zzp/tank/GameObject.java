package cn.zzp.tank;

import java.awt.Graphics;

public abstract class GameObject  {
	protected int x,y;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void paint(Graphics g);
	 
	}

	


