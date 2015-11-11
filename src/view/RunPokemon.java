package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Map;
import model.Trainer;

// this file plays the game.

public class RunPokemon extends JFrame{

	  public static void main(String[] args) {
		  RunPokemon window = new RunPokemon();
	      window.setVisible(true);
	  }

	  private MapView mapPanel;
	  private Map map;
	  private Trainer trainer;

	  public RunPokemon() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(256, 256 + 20); // +20 for title etc
	    
	    //Set location to middle of screen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	  
	    //setLocation(100, 30);
	    
	    /***
	     * Instantiate the juicy, delicious objects
	     */
	    map = new Map();
	    trainer = new Trainer("Testy");
	    mapPanel = new MapView(map);
	    
	    this.addKeyListener(new ArrowKeyListener());
	    add(mapPanel, BorderLayout.CENTER);
	    map.addObserver(mapPanel);
	    
	    this.setFocusable(true);
	  }

	  private class ArrowKeyListener implements KeyListener {
		
		long lastPressProcessed = 0;

	    @Override
	    public void keyPressed(KeyEvent ke) { 
 
	      // take inputs only 330ms at a time. 320ms to complete animation (I think.)
          if(System.currentTimeMillis() - lastPressProcessed > 330) {

	    	  if (ke.getKeyCode() == KeyEvent.VK_UP){
		    	  if (map.moveable("up")){
		    		  map.moveTrainer("up");
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 50){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 50 steps");
		    			  System.exit(0);
		    		  }
		    	  }
		      }
	    	  
		      if (ke.getKeyCode() == KeyEvent.VK_DOWN){
		    	  if (map.moveable("down")){
		    		  map.moveTrainer("down");
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 50){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 50 steps");
		    			  System.exit(0);
		    		  }
		    	  }
		      }
	 
		      if (ke.getKeyCode() == KeyEvent.VK_LEFT){
		    	  if (map.moveable("left")){
		    		  map.moveTrainer("left");
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 50){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 50 steps");
		    			  System.exit(0);
		    		  }
		    	  }	      
		      }
	
		      if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
		    	  if (map.moveable("right")){
		    		  map.moveTrainer("right");
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 50){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 50 steps");
		    			  System.exit(0);
		    		  }
		    	  }
		      }
		      
		      // check things - stub
		      if (ke.getKeyCode() == KeyEvent.VK_ENTER){
		    	  Object[] possibleValues = { "Check Pokemon", "Check Inventory", "Save and Quit" };
		    	  Object selectedValue = JOptionPane.showInputDialog(null,
		    	  "Steps Taken: " + trainer.getSteps(), "Input",
		    	  JOptionPane.INFORMATION_MESSAGE, null,
		    	  possibleValues, possibleValues[0]);
		      }
		      
              lastPressProcessed = System.currentTimeMillis();
		      
          }   
	    }

	    @Override
	    public void keyTyped(KeyEvent ke) { }
	    
	    @Override
	    public void keyReleased(KeyEvent ke) { }
	  }
	  
	}
