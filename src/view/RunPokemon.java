package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Map;
import model.Trainer;

// this file plays the game.

public class RunPokemon extends JFrame{

	  // main game loop
	  public static void main(String[] args) {

			int reload = JOptionPane.OK_OPTION;
			File f = new File("trainerSave.txt");
			if(f.exists() && !f.isDirectory()) {
	            reload = JOptionPane.showConfirmDialog(null, "Would you like to start from your previous save?", "Reload?", JOptionPane.OK_CANCEL_OPTION);
	            if (reload == JOptionPane.OK_OPTION){
					try{
						FileInputStream fis1 = new FileInputStream("mapSave.txt");
						ObjectInputStream ois1 = new ObjectInputStream(fis1);
						Map savedMap = (Map) ois1.readObject();
						ois1.close();
						fis1.close();
						
						FileInputStream fis2 = new FileInputStream("trainerSave.txt");
						ObjectInputStream ois2 = new ObjectInputStream(fis2);
						Trainer savedTrainer = (Trainer) ois2.readObject();
						ois2.close();
						fis2.close();
						
						// create object by passing these params 
			      		RunPokemon window = new RunPokemon(savedMap, savedTrainer);
			    	    window.setVisible(true);
			    	    
					}catch(Exception e){
						e.printStackTrace();
					}
	            }else{
	      		  RunPokemon window = new RunPokemon();
	    	      window.setVisible(true);
	            }
			}else{
      		  RunPokemon window = new RunPokemon();
    	      window.setVisible(true);
			}
	  }

	  // our instance vars
	  private MapView mapPanel;
	  private Map map;
	  private Trainer trainer;

	  // initial constructor, does not take a map or trainer
	  public RunPokemon() {
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		this.addWindowListener(new CloseButtonListener());
	    add(mapPanel, BorderLayout.CENTER);
	    map.addObserver(mapPanel);
	    
	    this.setFocusable(true);
	  }
	  
	  // Constructor overload: this one takes the save state
	  public RunPokemon(Map aMap, Trainer aTrainer) {
		    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		    map = aMap;
		    /*
		    for (int i=0; i<map.getMapLength(); i++){
			    for (int j=0; j<map.getMapLength(); j++){
			    	System.out.print(map.getTileAt(i, j));
			    }
		    	System.out.println();

		    }
		    */
		    trainer = aTrainer;
		    mapPanel = new MapView(map);
		    
		    this.addKeyListener(new ArrowKeyListener());
			this.addWindowListener(new CloseButtonListener());
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
	  
	  private class CloseButtonListener implements WindowListener{

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to save", null, JOptionPane.YES_NO_CANCEL_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					// write trainer
					File f = new File("trainerSave.txt");
			        f.delete();
					try {
						FileOutputStream fos = new FileOutputStream("trainerSave.txt", false);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(trainer);
						oos.close();
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// write map
					File f2 = new File("mapSave.txt");
			        f2.delete();
					try {
						FileOutputStream fos = new FileOutputStream("mapSave.txt", false);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(map);
						oos.close();
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// exit
					System.exit(0);
				}
				if(reply == JOptionPane.NO_OPTION){
					// exit
					System.exit(0);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			  
		  }
	  
	}
