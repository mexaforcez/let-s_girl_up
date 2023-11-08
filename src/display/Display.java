package display;

//import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Display extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Dimension size = new Dimension(1000,600);
		
	public Display() {
		this.setting();
		//this.getContentPane().add(new Play(this));		
		this.getContentPane().add(new Game()); // เพิ่ม Game ลงใน JFrame
		
	}
	
	private void setting() {
		this.setTitle("Let's Girl Up!");
		this.setSize(size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(280,100);
		this.setVisible(true);
	}
	
	private void removeContent() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}
	
	public void endGame(long point) {
		removeContent();
		//removePlayPanel();
		this.getContentPane().add(new Menu(point,this));
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("PLAY")) {
			removeContent();
			this.getContentPane().add(new Game());
			revalidate();
		}
		else if(e.getActionCommand().equals("AGAIN ?")) {
			removeContent();
			Game game = new Game();
			//removePlayPanel();
			this.getContentPane().add(game);
			game.requestFocus();
			
		}
	}
	
}