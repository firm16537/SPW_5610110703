import java.awt.*;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Window{
	public static void main(String[] args){
		JFrame frame = new JFrame("SPACE HELL");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());

		GamePanel gp = new GamePanel();
		SpaceShip s = new SpaceShip(180, 560, 20, 20);

		gp.sprites.add(s);
		gp.updateGameUI();


		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
	
		
		
	}
}