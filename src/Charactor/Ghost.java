package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class Ghost{

	public int x ;
	public int y;
	public int health=180;
	public static int speed=100;
	
	private BufferedImage jumpImage;
	private boolean isJumping = false;
	
	private BufferedImage[] images = new BufferedImage[2];
    private int currentImageIndex = 0;
	
	public Ghost() {
		loadImages();
	}
	
	public Ghost(int x,int y) {
		this.x=x;
		this.y=y;
		loadImages();
	}
	
	 private void loadImages() {
	        try {
	            images[0] = ImageIO.read(new File("img\\left.png"));
	            images[1] = ImageIO.read(new File("img\\right2.png"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	public void jump(JPanel page) {
		this.y -= speed;
		isJumping = true;
		page.repaint(); 
		//--- fall ---
		Timer timer =new Timer(450,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					y += speed;
					isJumping = false;
					page.repaint();
			}
		});
		timer.setRepeats(false);
		timer.start();
		//--- change image while jumping ---
        Timer jumpImageTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jumpImage = getJumpImage();
                page.repaint();
            }
        });
        jumpImageTimer.setRepeats(false);
        jumpImageTimer.start();
	}
	public boolean isJumping() {
        return isJumping;
    }
	   public BufferedImage getJumpImage() {
	        BufferedImage image = null;
	        try {
	            image = ImageIO.read(new File("img\\girljump2.png"));
	            return image;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return image;
	    }
	
		public BufferedImage getImage() {
	        BufferedImage image = images[currentImageIndex];
	        currentImageIndex = (currentImageIndex + 1) % 2; // Alternate between 0 and 1
	        return image;
	    }
}