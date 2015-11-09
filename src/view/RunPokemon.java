package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Map;

// this file plays the game.

public class RunPokemon extends JFrame {

	  public static void main(String[] args) {
		  RunPokemon window = new RunPokemon();
	      window.setVisible(true);
	  }

	  private MapView mapPanel;
	  private Map map;

	  public RunPokemon() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(256, 256 + 20); // +20 for title etc
	    
	    //Set location to middle of screen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	  
	    //setLocation(100, 30);
	    this.addKeyListener(new ArrowKeyListener());
	    map = new Map();
	    mapPanel = new MapView(map);
	    add(mapPanel, BorderLayout.CENTER);
	    map.addObserver(mapPanel);
	    this.setFocusable(true);
	  }

	  private class ArrowKeyListener implements KeyListener {

	    @Override
	    public void keyPressed(KeyEvent ke) {

	      if (ke.getKeyCode() == KeyEvent.VK_UP){
	    	  if (map.moveable("up")){
	    		  map.moveTrainer("up");
	    	  }
	      }

	      if (ke.getKeyCode() == KeyEvent.VK_DOWN){
	    	  if (map.moveable("down")){
	    		  map.moveTrainer("down");
	    	  }
	      }
 
	      if (ke.getKeyCode() == KeyEvent.VK_LEFT){ // mirror
	    	  if (map.moveable("left")){
	    		  map.moveTrainer("left");
	    	  }	      
	      }

	      if (ke.getKeyCode() == KeyEvent.VK_RIGHT){ // mirror
	    	  if (map.moveable("right")){
	    		  map.moveTrainer("right");
	    	  }
	      }
	      
	      // check things
	      if (ke.getKeyCode() == KeyEvent.VK_ENTER){
	    	  Object[] possibleValues = { "Check Pokemon", "Check Inventory", "Save and Quit" };
	    	  Object selectedValue = JOptionPane.showInputDialog(null,
	    	  "Choose one", "Input",
	    	  JOptionPane.INFORMATION_MESSAGE, null,
	    	  possibleValues, possibleValues[0]);
	      }
	    	  
	    }

	    @Override
	    public void keyTyped(KeyEvent e) { }
	    @Override
	    public void keyReleased(KeyEvent e) { }
	  }
	}
