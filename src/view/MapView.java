package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Direction;
import model.Map;

public class MapView extends JPanel implements Observer {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	  private Map map;
	  private Image plain, grass, bush, pokeball, runningShoes, fishingPole, costumeChange;
	  private Image player, player1, player2, player3, player4, player5, player6, player7, player8, player9, player10, player11;
	  private int X, Y, tic, n;
	  private Direction direction;
	  private Timer timer;

	  public MapView(Map map) {
	    this.map = map; 
	    X = this.map.getTrainerX() * 16;
	    Y = this.map.getTrainerY() * 16;
	    try {
	      player = ImageIO.read(new File("./images/trainerImages/trainerForward1.png"));
	      player1 = ImageIO.read(new File("./images/trainerImages/trainerForward2.png"));
	      player2 = ImageIO.read(new File("./images/trainerImages/trainerForward3.png"));
	      player3 = ImageIO.read(new File("./images/trainerImages/trainerLeft1.png"));
	      player4 = ImageIO.read(new File("./images/trainerImages/trainerLeft2.png"));
	      player5 = ImageIO.read(new File("./images/trainerImages/trainerLeft3.png"));
	      player6 = ImageIO.read(new File("./images/trainerImages/trainerBack1.png"));
	      player7 = ImageIO.read(new File("./images/trainerImages/trainerBack2.png"));
	      player8 = ImageIO.read(new File("./images/trainerImages/trainerBack3.png"));
	      player9 = ImageIO.read(new File("./images/trainerImages/trainerRight1.png"));
	      player10 = ImageIO.read(new File("./images/trainerImages/trainerRight2.png"));
	      player11 = ImageIO.read(new File("./images/trainerImages/trainerRight3.png"));
	      plain = ImageIO.read(new File("./images/plain.png"));
	      grass = ImageIO.read(new File("./images/grass.png"));
	      bush = ImageIO.read(new File("./images/bush.png"));
	      pokeball = ImageIO.read(new File("./images/pokeball.png"));
	      fishingPole = ImageIO.read(new File("./images/fishingPole.png"));
	      costumeChange = ImageIO.read(new File("./images/costumeChange.png"));
	      pokeball = ImageIO.read(new File("./images/pokeball.png"));
	    } catch (IOException e) { 
	      e.printStackTrace();
	    }
	    // call it to initiate
	    timer = new Timer(40, new TimerListener());
	    repaint();
	  }

	  // repaints with animation
	  @Override
	  public void update(Observable observable, Object dir) {
	    map = (Map) observable;
	    direction = (Direction) dir;
	    drawBoardWithAnimation();
	  }
	  
	  // timer based repaints
	  private void drawBoardWithAnimation() {
		n = 8; 
		tic = 1;
		timer.start();
	  }

	    // called by repaint -- paint errthang
	    public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    
			// we're going to build and draw this.
			Image[][] imageGrid = new Image[map.getMapLength()][map.getMapLength()];
			
			// start with terrain. find terrain
			for (int i=0; i<map.getMapLength(); i++){
				for (int j=0; j<map.getMapLength(); j++){
					
					if(map.getTileAt(i, j).equals("G")){
						imageGrid[i][j] = grass;
					}
					else if(map.getTileAt(i, j).equals("B")){ 
						imageGrid[i][j] = bush;
					}
					else if(map.getTileAt(i, j).equals("I")){ 
						imageGrid[i][j] = pokeball;
					}
					else{
						imageGrid[i][j] = plain;
					}		
				}
			}
			
			// set all tiles to plain, so you can draw items
			// draw the current active map
		    // then, display the dude. always on top
			
			for (int r = 0; r < 512; r += 16){
			    for (int c = 0; c < 512; c += 16){
		    		  g2.drawImage(plain, r, c, null);
				}		
			}
			
			if (X < 256 && Y < 256){
				// top left
				for (int r = 0; r < 256; r += 16){
				    for (int c = 0; c < 256; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r, c, null);
					}		
				}
				if (direction == Direction.SOUTH){						
					if (tic < 4){
						g2.drawImage(player1, X, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player2, X, Y, null);
					}else{
						g2.drawImage(player, X, Y, null);
					}
				}else if (direction == Direction.EAST){
					if (tic < 4){
						g2.drawImage(player10, X, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player11, X, Y, null);
					}else{
						g2.drawImage(player9, X, Y, null);
					}
				}else if (direction == Direction.NORTH){
					if (tic < 4){
						g2.drawImage(player7, X, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player8, X, Y, null);
					}else{
						g2.drawImage(player6, X, Y, null);
					}
				}else if (direction == Direction.WEST){
					if (tic < 4){
						g2.drawImage(player4, X, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player5, X, Y, null);
					}else{
						g2.drawImage(player3, X, Y, null);
					}
				}else{
					g2.drawImage(player, X, Y, null);
				}
			    
			}else if(X > 256 && Y < 256){
				// top right
				for (int r = 256; r < 512; r += 16){
				    for (int c = 0; c < 256; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r-256, c, null);
					}		
				}
				if (direction == Direction.SOUTH){
					if (tic < 4){
						g2.drawImage(player1, X-256, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player2, X-256, Y, null);
					}else{
						g2.drawImage(player, X-256, Y, null);
					}
				}else if (direction == Direction.EAST){
					if (tic < 4){
						g2.drawImage(player10, X-256, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player11, X-256, Y, null);
					}else{
						g2.drawImage(player9, X-256, Y, null);
					}
				}else if (direction == Direction.NORTH){
					if (tic < 4){
						g2.drawImage(player7, X-256, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player8, X-256, Y, null);
					}else{
						g2.drawImage(player6, X-256, Y, null);
					}
				}else if (direction == Direction.WEST){
					if (tic < 4){
						g2.drawImage(player4, X-256, Y, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player5, X-256, Y, null);
					}else{
						g2.drawImage(player3, X-256, Y, null);
					}
				}else{
					g2.drawImage(player, X, Y, null);
				}
			    
			}else if(X < 256 && Y > 256){
				// bottom left
				for (int r = 0; r < 256; r += 16){
				    for (int c = 256; c < 512; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r, c-256, null);
					}		
				}
				if (direction == Direction.SOUTH){
					if (tic < 4){
						g2.drawImage(player1, X, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player2, X, Y-256, null);
					}else{
						g2.drawImage(player, X, Y-256, null);
					}
				}else if (direction == Direction.EAST){
					if (tic < 4){
						g2.drawImage(player10, X, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player11, X, Y-256, null);
					}else{
						g2.drawImage(player9, X, Y-256, null);
					}
				}else if (direction == Direction.NORTH){
					if (tic < 4){
						g2.drawImage(player7, X, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player8, X, Y-256, null);
					}else{
						g2.drawImage(player6, X, Y-256, null);
					}
				}else if (direction == Direction.WEST){
					if (tic < 4){
						g2.drawImage(player4, X, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player5, X, Y-256, null);
					}else{
						g2.drawImage(player3, X, Y-256, null);
					}
				}else{
					g2.drawImage(player, X, Y, null);
				}
			    
			}else if(X > 256 && Y > 256){
				// bottom right
				for (int r = 256; r < 512; r += 16){
				    for (int c = 256; c < 512; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r-256, c-256, null);
					}		
				}
				if (direction == Direction.SOUTH){
					if (tic < 4){
						g2.drawImage(player1, X-256, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player2, X-256, Y-256, null);
					}else{
						g2.drawImage(player, X-256, Y-256, null);
					}
				}else if (direction == Direction.EAST){
					if (tic < 4){
						g2.drawImage(player10, X-256, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player11, X-256, Y-256, null);
					}else{
						g2.drawImage(player9, X-256, Y-256, null);
					}
				}else if (direction == Direction.NORTH){
					if (tic < 4){
						g2.drawImage(player7, X-256, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player8, X-256, Y-256, null);
					}else{
						g2.drawImage(player6, X-256, Y-256, null);
					}
				}else if (direction == Direction.WEST){
					if (tic < 4){
						g2.drawImage(player4, X-256, Y-256, null);
					}else if (tic > 4 && tic < 8){
						g2.drawImage(player5, X-256, Y-256, null);
					}else{
						g2.drawImage(player3, X-256, Y-256, null);
					}
				}else{
					g2.drawImage(player, X, Y, null);
				}
			    

			}
			
	    }
	  
		// move around
	    private class TimerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tic <= n){
					if (direction == Direction.NORTH){
						Y = Y - 2;
						repaint();
						tic++;
					}
					if (direction == Direction.WEST){
						X = X - 2;
						repaint();
						tic++;
					}
					if (direction == Direction.SOUTH){
						Y = Y + 2;
						repaint();
						tic++;
					}
					if (direction == Direction.EAST){
						X = X + 2;
						repaint();
						tic++;
					}
				}else{
					timer.stop();
				}
				
			}
		}

	    
	    public void speedUpTrainer(){
	    	timer = new Timer(1, new TimerListener());
	    }
	    
	    public void changeCostume(){
	    	try {
				player = ImageIO.read(new File("./images/trainerRuby.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	repaint();
	    }
}
