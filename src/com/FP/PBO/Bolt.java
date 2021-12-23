package com.FP.PBO;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Bolt {
	int MX, MY;
	Rectangle rectangle;
	Random random = new Random();
	
	Bolt(){}
	
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
		setMy(30);
		setMx(30 + random.nextInt(Game.WIDTH - 40));
	}
	
	void drawOn(Graphics2D graphics) {
		GradientPaint gp = new GradientPaint(
				MX-15, MY-10,
				Color.RED,
				MX+5, MY+15/2,
				Color.YELLOW, true);
		graphics.setPaint(gp);
		graphics.fillRect(MX, MY, 5, 20);
		rectangle = new Rectangle(MX, MY, 10, 15/2);
	}
}
