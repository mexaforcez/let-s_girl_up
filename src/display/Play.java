package display;


import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Element.EleButton;
import Element.EleLabel;


public class Play extends JPanel {

	    private static final long serialVersionUID = 1L;
//	    public ImageIcon bg = new ImageIcon(this.getClass().getResource("img/dir.png"));
		
		public Play() {
			//----
		}
		
		public Play(ActionListener main) {
			try {
					this.setBackground(new Color(255, 128, 0));
					this.setBounds(0,0,1000,600);
					this.setFocusable(true);
					this.setLayout(null);
					
					EleLabel status = new EleLabel("Let's Girl Up!",30,380,150,200,100);
					status.setForeground(Color.white);
										
					EleButton play = new EleButton("PLAY",15,380,300,200,50);
					play.addActionListener(main);

					
					this.add(status);
					this.add(play);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		/*public void paintComponent(Graphics g) {
			super.paintComponent(g);
//			g.drawRect(50, 50, 100, 100);
//			g.drawImage(bg.getImage(), 0,0,1000,1000,this);
			
		}*/
		
}
