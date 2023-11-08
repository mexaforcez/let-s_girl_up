package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Charactor.Point;
import Element.Element;
import event.Event;

public class Game extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	private static int speed = 50,dogSize = 70 ,waveHeight = 40, pointHeight = 35;
	private static int base=400,xStart = 1000;
	private long point = 0,lastPress=0;
	
	private Ghost dog = new Ghost(100,base-50);
	static Display display;
	
//	------------------Wave SIze ----------------------------
	private Wave[] waveSet = makeWave(4);
//--------------------Environment---------------------------
	private Environment[] envSet = makeEnv(2,Environment.CLOUD);
	private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,5);
	private Environment moon = new Environment(xStart+600,base-150,this,Environment.MOON,8);
//-----------------Point Size-------------------------------
    private Point[] pointset = makePoint(4);
    
	private boolean gameStarted = false;
    private Play playPanel;
    

    
	public Game(){
		this.setBounds(0,0,1000,600);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setFocusable(true);
		
		playPanel = new Play(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePlayPanel();
                gameStarted = true;
            }
        });
        this.add(playPanel);
	}

	private void removePlayPanel() {
	        this.remove(playPanel);
	    }
	
	@Override
	public void paint(Graphics g) {
			try {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				if(gameStarted) 
				{
					this.drawBackground(g2);
				//---POINT----
					g2.setFont(Element.getFont(30));
					g2.setColor(Color.white);
					g2.drawString("Point : "+point,750,40);
				//--- dog --
					g2.setColor(Color.RED);
					drawDogHealth(g2);
					if (dog.isJumping()) {
		                g2.drawImage(dog.getJumpImage(), dog.x, dog.y, dogSize, dogSize, null);
		            } else {
		                g2.drawImage(dog.getImage(), dog.x, dog.y, dogSize, dogSize, null);
		            }
				//----Wave----
					for(Wave item : waveSet) {
						drawWave(item,g2);
					}
					//this.point+=1;
				//---Point----
					for(Point item : pointset) {
						drawPoint(item,g2);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		
	private void drawBackground(Graphics2D g2) throws IOException {

		 	g2.drawImage(ImageIO.read(new File("img\\dark.png")), 0, 0, 2000, 1000, null);
	        g2.drawImage(building.getImage(), building.x, building.y, 400, 200, null);
	        g2.drawImage(moon.getImage(), moon.x, moon.y, 400, 200, null);
	        g2.drawImage(ImageIO.read(new File("img\\groud.png")), 0, base + 10, 2000, 220, null);
	        for (Environment item : envSet) {
	            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
	        }
	        if(this.point >= 4) {
	        	g2.drawImage(ImageIO.read(new File("img\\sky.png")), 0, 0, 2000, 1000, null);
	        	g2.drawImage(building.getImage(), building.x, building.y, 400, 200, null);
		        g2.drawImage(moon.getImage(), moon.x, moon.y, 400, 200, null);
		        g2.drawImage(ImageIO.read(new File("img\\dir.png")), 0, base + 10, 2000, 220, null);
		        for (Environment item : envSet) {
		            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
		        }
	        }
	        if(this.point >= 7) {
	        	g2.drawImage(ImageIO.read(new File("img\\yellow.png")), 0, 0, 2000, 1000, null);
	        	g2.drawImage(building.getImage(), building.x, building.y, 400, 200, null);
		        g2.drawImage(moon.getImage(), moon.x, moon.y, 400, 200, null);
		        g2.drawImage(ImageIO.read(new File("img\\groud.png")), 0, base + 10, 2000, 220, null);
		        for (Environment item : envSet) {
		            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
		        }
	        }
	        if(this.point >= 10) {
	        	g2.drawImage(ImageIO.read(new File("img\\green.png")), 0, 0, 2000, 1000, null);
	        	g2.drawImage(building.getImage(), building.x, building.y, 400, 200, null);
		        g2.drawImage(moon.getImage(), moon.x, moon.y, 400, 200, null);
		        g2.drawImage(ImageIO.read(new File("img\\pink.png")), 0, base + 10, 2000, 220, null);
		        for (Environment item : envSet) {
		            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
		        }
	        }
		
		
	}
	
	private void drawDogHealth(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(new File("img\\heart.png")),10,20, 20,20,null);
			g2.setStroke(new BasicStroke(18.0f));
			g2.setColor(new Color(255, 128, 0));
			g2.drawLine(60, 30,60+dog.health,30);	
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(6.0f));
			g2.drawRect(50,20, 200,20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Wave[] makeWave(int size) {
		Wave[] waveSet = new Wave[size];
		int far = 500;
		for(int i=0;i<size;i++) {
			waveSet[i] = new Wave(xStart+far,base,speed,this);
			far+=500;
		}
		return waveSet;
	}
	
	//new point
	private Point[] makePoint(int size) {
		Point[] pointset = new Point[size];
		int far = 700;
		for(int i=0; i<size; i++) {
			pointset[i] = new Point(xStart+far,base,speed,this);
			far+=800;
		}
		
		return pointset;
	}
	
	private Environment[] makeEnv(int size,int eType){
		Environment[] envSet = new Environment[size];
		int far = 0;
		for(int i=0;i<size;i++) {
			envSet[i] = new Environment(xStart+far,20,this,eType,10);
			far+=600;
		}
		return envSet;
	}
	
	private void drawWave(Wave wave,Graphics2D g2) {
			g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),40,waveHeight+10,null);
			if(Event.checkHit(dog,wave,dogSize,waveHeight)){
					g2.setColor(new Color(255, 128, 0));
					g2.fillRect(0, 0,1000,1000);			
					dog.health-=20;
					if(dog.health<=0) {
						display.endGame(this.point);
						dog.health = new Ghost().health;
						this.point = 0;	
					}
			}
	}
	
	//draw point
	private void drawPoint(Point point,Graphics2D g2) {
		g2.drawImage(point.getImage(),point.x,(point.y-pointHeight),40,pointHeight+10,null);
		if(Event.checPoint(dog, point, dogSize, pointHeight)) {
			this.point += 1;
			point.x = -200; // ขยับ point ที่ชนออกจากหน้าจอ
	        point.y = -200;
	        if (this.point > 10) {
	            // สร้าง point ใหม่เมื่อ point เกิน 10
	            int far = 500;
	            for (int i = 0; i < pointset.length; i++) {
	                if (pointset[i].x < 0) {
	                    pointset[i] = new Point(xStart + far, base, speed, this);
	                    far += 800;
	                }
	            }
	        }
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(System.currentTimeMillis() - lastPress > 600) {
			if(e.getKeyCode()==32||e.getKeyCode()==38) {
					dog.jump(this);
					lastPress = System.currentTimeMillis();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//---
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//---
	}
	
	public static void main(String[] arg) {
		 display = new Display();
	}
}