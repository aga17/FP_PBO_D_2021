package com.FP.PBO;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Drop {
	int MX, MY;
	Rectangle rectangle;
	Random random = new Random();
	
	Drop(){}
	
	boolean fallIn(Bowl bowl) {
		return rectangle.intersects(bowl.rectangle);
	}
	
	void setMx(int dx) {
		MX = dx;
	}
	
	void setMy(int dy) {
		MY = dy;
	}
	
	public void reset() {
		setMy(10);
		setMx(30 + random.nextInt(Game.WIDTH - 40));
	}
	
	void drawOn(Graphics2D graphics) {
		GradientPaint gp = new GradientPaint(
				MX, MY, 
				Color.WHITE.brighter(), 
				MX + 5, MY + 15 / 2, 
				Color.CYAN.brighter()
				);
		graphics.setPaint(gp);
		graphics.fillOval(MX, MY, 10, 20);
		rectangle = new Rectangle(MX, MY, 10, 15/2);
	}
}
