package com.FP.PBO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class Game extends JPanel implements Runnable{
	Thread thread;
	Graphics2D graphics;
	int x, y;
	Bowl bowl = new Bowl();
	int NUM = 5;
	Drop drop[] = new Drop[NUM];
	Bolt bolt[] = new Bolt[300];
	DropMover Dmover[] = new DropMover[NUM];
	BoltMover Bmover[] = new BoltMover[300];
	boolean inside = false;
	Timer timer[] = new Timer[NUM];
	static int WIDTH = 370, HEIGHT = 605;
	int speed = 10;
	int lifes = 3;
	
	public Game() {
		bowl.setMx(30);
		bowl.setMy(200);
		setOpaque(false);
        try{
            setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma", 1, 24), new Color(204, 0, 0)));
            for(LookAndFeelInfo inf: UIManager.getInstalledLookAndFeels()){
                if(inf.getName().equals("Nimbus")){
                    UIManager.setLookAndFeel(inf.getClassName());
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
		
		for(int i=0; i<drop.length; ++i) {
			drop[i] = new Drop();
			drop[i].setMy(30);
			drop[i].reset();
		}
		
		for(int i=0; i<bolt.length; ++i) {
			bolt[i] = new Bolt();
			bolt[i].setMy(30);
			bolt[i].reset();
		}
		
		for(int i=0; i<drop.length; ++i) {
			Dmover[i] = new DropMover(drop[i], bowl);
			Dmover[i].setInitialDelay((i+1) * 1500);
			Dmover[i].move();
		}
		
		for(int i=0; i<bolt.length; ++i) {
			Bmover[i] = new BoltMover(bolt[i], bowl);
			Bmover[i].setInitialDelay((i+1) * 5000);
			Bmover[i].move();
		}
		
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				x = (int)me.getPoint().getX();
				if(bowl.contains(me.getPoint())) {
					inside = true;
				}
			}
			
			public void mouseReleased(MouseEvent me) {
				inside = false;
			}
		};
		
		addMouseListener(ml);
		
		MouseMotionListener mll = new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
				if(inside == true) {
					bowl.setMx((int)me.getPoint().getX());
				}
			}
		};
		
		addMouseMotionListener(mll);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics graphics2) {
		graphics = (Graphics2D)graphics2;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(graphics);
		
		bowl.setMy(getHeight()-100);
		
		ImageIcon background = new ImageIcon("Assets/background.png");
		background.paintIcon(this, graphics, 0, 0);
		
		bowl.drawOn(graphics);
		
		for(int i=0; i<drop.length; ++i) {
			drop[i].drawOn(graphics);
		}
		
		for(int i=0; i<bolt.length; ++i) {
			bolt[i].drawOn(graphics);
		}
	}
	
	public void run() {
		try {
			while(true) {
				Thread.sleep(speed);
				if(bowl.lifes == 0) {
					for(int i=0; i<drop.length; ++i) {
						drop[i].reset();
						Dmover[i].stop();
					}
					JOptionPane.showMessageDialog(null, "Your Score Was " + bowl.score);
					int a = JOptionPane.showConfirmDialog(null, "Do You Want To Restart The Game?", "Game Over", JOptionPane.OK_CANCEL_OPTION);
					
					if(a == JOptionPane.OK_OPTION) {
						for(int i=0; i<drop.length; ++i) {
							Dmover[i].setInitialDelay((i+1) * 1500);
							Dmover[i].move();
						}
						
						for(int i=0; i<bolt.length; ++i) {
							Bmover[i].setInitialDelay((i+1) * 5000);
							Bmover[i].move();
						}
						
						bowl.score = -10;
						bowl.lifes = 3;
						bowl.updateScore();
					}
					if(a == JOptionPane.CANCEL_OPTION) {
						System.exit(0);
					}
				}
				repaint();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String args[]) {
		
		JFrame jfm = new JFrame();
		JLabel l= new JLabel("Your Score Along With Life Is Displayed Here");
		JLabel label = new JLabel();
		
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setLayout(new BorderLayout());
		
        l.setBorder(BorderFactory.createTitledBorder(null, "U-Done", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma", 1, 24), Color.BLACK));
        jfm.add(l,BorderLayout.NORTH);
		
		label.setBounds(0, 0, 370, 135);
		label.setIcon(new ImageIcon("Assets/top.png"));
		label.setVisible(true);
		jfm.getContentPane().add(label);
		
		final Game UDone = new Game();
		UDone.bowl.setOutputComponent(l);
		
		KeyListener kl = new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					UDone.bowl.setMx(UDone.bowl.MX+2);
				}
				if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
					UDone.bowl.setMx(UDone.bowl.MX-2);
				}
			}
		};
		
		jfm.addKeyListener(kl);
		jfm.add(UDone);
		jfm.setSize(370, 605);
		jfm.setVisible(true);
		jfm.setResizable(false); 
		jfm.setLocationRelativeTo(null);
	}
}
