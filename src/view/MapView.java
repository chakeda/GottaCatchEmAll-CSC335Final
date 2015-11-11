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

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Map;

public class MapView extends JPanel implements Observer {

	  private Map map;
	  private Image player, plain, grass, bush;
	  private int X, Y, tic, n;
	  private String direction;
	  private Timer timer;

	  public MapView(Map map) {
	    this.map = map; // Avoid null pointer when board is first drawn
	    X = map.getTrainerX() * 16;
	    Y = map.getTrainerY() * 16;
	    try {
	      player = ImageIO.read(new File("./images/trainer.gif"));
	      plain = ImageIO.read(new File("./images/plain.png"));
	      grass = ImageIO.read(new File("./images/grass.png"));
	      bush = ImageIO.read(new File("./images/bush.png"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    // call it to initiate
	    repaint();
	  }

	  // repaints with animation
	  @Override
	  public void update(Observable observable, Object dir) {
	    map = (Map) observable;
	    direction = (String) dir;
	    drawBoardWithAnimation();
	  }
	  
	  // timer based repaints
	  private void drawBoardWithAnimation() {
		n = 8; 
		tic = 1;
		timer = new Timer(40, new TimerListener());
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
					
					if(map.getTileAt(i, j) == "G"){
						imageGrid[i][j] = grass;
					}
					else if(map.getTileAt(i, j) == "B"){ 
						imageGrid[i][j] = bush;
					}
					else{
						imageGrid[i][j] = plain;
					}
				}
			}
			
			// draw the current active map
		    // then, display the dude. always on top
			if (X < 256 && Y < 256){
				// top left
				for (int r = 0; r < 256; r += 16){
				    for (int c = 0; c < 256; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r, c, null);
					}		
				}
			    g2.drawImage(player, X, Y, null);
			    
			}else if(X > 256 && Y < 256){
				// top right
				for (int r = 256; r < 512; r += 16){
				    for (int c = 0; c < 256; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r-256, c, null);
					}		
				}
			    g2.drawImage(player, X-256, Y, null);
			    
			}else if(X < 256 && Y > 256){
				// bottom left
				for (int r = 0; r < 256; r += 16){
				    for (int c = 256; c < 512; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r, c-256, null);
					}		
				}
			    g2.drawImage(player, X, Y-256, null);
			    
			}else if(X > 256 && Y > 256){
				// bottom right
				for (int r = 256; r < 512; r += 16){
				    for (int c = 256; c < 512; c += 16){
			    		  g2.drawImage(imageGrid[c/16][r/16], r-256, c-256, null);
					}		
				}
			    g2.drawImage(player, X-256, Y-256, null);
			    
			}
			
	    }
	  
		// move around
		private class TimerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tic <= n){
					if (direction == "up"){
						Y = Y - 2;
						repaint();
						tic++;
					}
					if (direction == "left"){
						X = X - 2;
						repaint();
						tic++;
					}
					if (direction == "down"){
						Y = Y + 2;
						repaint();
						tic++;
					}
					if (direction == "right"){
						X = X + 2;
						repaint();
						tic++;
					}
				}else{
					timer.stop();
				}
				
			}
		}

}
