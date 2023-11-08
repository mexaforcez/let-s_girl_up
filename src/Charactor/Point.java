package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Point extends Wave{

	public Point(int x, int y, int speed, JPanel page) {
		super(x, y, speed, page);
		// TODO Auto-generated constructor stub
	}
	public void move(JPanel page) {
		 this.timeMove = new Timer(speed,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(x<=0) {
						x = (int) (1000+(300+Math.random()*1000));
					}
					x -= 30;
					page.repaint();
			}
		});
		 this.timeMove.start();
	}
	
	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			 image = ImageIO.read(new File("img\\coin.png"));
			 return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
