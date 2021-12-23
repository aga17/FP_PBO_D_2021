package com.FP.PBO;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Bowl {
	int MX, MY;
	Rectangle rectangle;
	JLabel label;
	int score = 0, lifes = 3, level = 1;
	
	Bowl(){}
	
	void setMx(int dx) {
		MX = dx;
	}
	
	void setOutputComponent(JLabel lbl) {
		label = lbl;
	}
	
	void updateScore() {
		label.setText("Score = " + (score += 10) + "    Lifes = " + (lifes) + "    Level = " + (level));
		if(level == 2) {
			
		}
	}
	
	void updateLife() {
		label.setText("Score = " + (score += 10) + "    Lifes = " + (lifes) + "    Level = " + (level));
	}
	
	void setMy(int dy) {
		MY = dy;
	}
	
	boolean contains(Point p){
        return rectangle.contains(p);
    }
	
	void drawOn(Graphics2D graphics) {
		GradientPaint gp1= new GradientPaint(MX-15,MY-10,Color.LIGHT_GRAY,MX-15+30/2,MY-10+30/2,Color.WHITE,true);
		graphics.setPaint(gp1);
		graphics.fillArc(MX-15, MY-10, 30, 30, 0, -180);
        GradientPaint gp2= new GradientPaint(MX,MY,Color.WHITE,MX,MY+10,Color.DARK_GRAY.brighter());
        graphics.setPaint(gp2);
        graphics.fillOval(MX-15, MY, 30, 10);
        rectangle = new Rectangle(MX-15,MY,30,20);
	}
}
