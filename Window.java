import java.awt.Color;
import javax.swing.JFrame;

public class Window{
	public static void main(String[] args){
		JFrame frame = new JFrame("SPACE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 700);

		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		
	
		
		
	}
}