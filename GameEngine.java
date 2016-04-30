import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.io.*;
import java.lang.*;

public class GameEngine implements KeyListener, GameReporter{
	
	GamePanel gp;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bonus> bonus = new ArrayList<Bonus>(); 
	private SpaceShip v;	
	private Timer timer;
	private double difficulty = 0.1;
	private double bonusx = 0.01; // Set Bonus
	private long score = 0;



	private int life = 3;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateBonus(){ // bonus generate
		Bonus b = new Bonus((int)(Math.random()*390), 30);
		gp.sprites.add(b);
		bonus.add(b);
	}
	
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		if(Math.random() < bonusx){ // set bonus prop.
			generateBonus();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(score >= 5000 && score < 10000){
				difficulty = 0.2;
			}
			if(score >= 10000 && score < 15000){
				difficulty = 0.3;
			}
			if(score >= 15000 && score < 20000){
				difficulty = 0.4;
			}
			if(score >= 20000 && score < 30000){
				difficulty = 0.5;
			}
			if(score >= 3000 && score < 45000){
				difficulty = 0.6;
			}
			if(score >= 45000 && score < 60000){
				difficulty = 0.7;
			}
			if(score >= 60000 && score < 850000){
				difficulty = 0.8;
			}
			if(score >= 85000 ){
				difficulty = 0.99;
			}
				
  

				if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}

		gp.updateGameUI(this);
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(e.getClash() == true){
				life--;
				e.setClash(false);
				}
				if(life < 1){
				die();
				}


				return;
			}
		}

		Iterator<Bonus> b_iter = bonus.iterator(); //bonus process
		while(b_iter.hasNext()){
			Bonus b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){ // If move to end display
				b_iter.remove();  // remove
				gp.sprites.remove(b); //remove
			}

			er = b.getRectangle(); //check clash?
			if(er.intersects(vr)){
				gp.sprites.remove(b); //clash and remove
				b_iter.remove();
				score += 1000; 
			}
		}				
	}

	public void die(){
		if(score <= 1000){
			JOptionPane.showMessageDialog("You score :" + score + "Score" );
		}
		if(score >= 10000 && score < 40000){ 
			JOptionPane.showMessageDialog("You score :" + score + "Score");
		}
		else
			JOptionPane.showMessageDialog("You score :" + score + "Score");

		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.moveX(-2);
			break;
		case KeyEvent.VK_RIGHT:
			v.moveX(2);
			break;
		}
	}

	public long getScore(){
		return score;
	}

	public	int getLife(){
		return life;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
