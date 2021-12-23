package com.FP.PBO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BoltMover {
	ActionListener al;
	Timer timer;
	Bolt bolt;
	Bowl bowl; 
	boolean allowed = true;
	
	public BoltMover(Bolt bl, Bowl bwl) {
		bolt = bl;
		bowl = bwl;
		al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				bolt.setMy(bolt.MY+3);
				if(isAllowed()) {
					if(bolt.fallIn(bowl)) {
						bolt.reset();
						bowl.lifes -= 1;
						bowl.updateLife();
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
