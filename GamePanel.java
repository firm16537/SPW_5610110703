import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	Graphics2D enemyfade;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	BufferedImage bg;


	public GamePanel() {
		bi = new BufferedImage(400, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK); // set color blackground
		enemyfade = (Graphics2D) bi.getGraphics();

		try{
			bg = ImageIO.read(new File("C:/Users/USER/Documents/GitHub/SPW_5610110703/space02.jpg"));
		}
		catch(IOException d){

		}
		
	}
	

	public void updateGameUI(GameEngine reporter){
		big.clearRect(0, 0, 400, 650);
		big.drawImage(bg, 0, 0, 400, 650, null); //<<BG
		big.setColor(Color.GREEN);		// set color score
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20); //count score
		big.drawString(String.format("%d", reporter.getLife()), 30, 20); 
		for(Sprite s : sprites){
			if(s.width == 20)
				s.draw(big);
			else
				s.draw(enemyfade);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
