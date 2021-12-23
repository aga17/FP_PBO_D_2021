package com.FP.PBO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class DropMover {
	ActionListener al;
	Timer timer;
	Drop drop;
	Bowl bowl;
	boolean allowed = true;
	
	public DropMover(Drop dr, Bowl bwl) {
		drop = dr;
		bowl = bwl;
		al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				drop.setMy(drop.MY+2);
				if(isAllowed()) {
					if(drop.fallIn(bowl)) {
						drop.reset();
						bowl.updateScore();
					}
				}
				
				if(bowl.score >= 100) {
					drop.setMy(drop.MY+2);
					if(bowl.score == 100) {
						bowl.level++;
						bowl.updateScore();
					}
				}
				
				if(bowl.score >= 200) {
					drop.setMy(drop.MY+3);
					if(bowl.score == 200) {
						bowl.level++;
						bowl.updateScore();
					}
				}
				
				if(bowl.score >= 400) {
					drop.setMy(drop.MY+4);
					if(bowl.score == 400) {
						bowl.level++;
						bowl.updateScore();
					}
				}
				
				if(bowl.score >= 700) {
					drop.setMy(drop.MY+5);
					if(bowl.score == 700) {
						bowl.level++;
						bowl.updateScore();
					}
				}
			}
		};
		timer = new Timer(10, al);
	}
	void setInitialDelay(int i) {
		timer.setInitialDelay(i);
	}
	
	boolean isAllowed() {
		return allowed;
	}
	
	void move() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
}
