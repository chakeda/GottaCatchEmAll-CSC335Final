package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Direction;
import model.Map;
import model.Pokemon;
import model.Trainer;
import model.pokemon.Scyther;

// this file plays the game.

public class RunPokemon extends JFrame{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	  private BattleView battlePanel;
	  private JPanel bothViews;
	  private Map map;
	  private Trainer trainer;

	  // initial constructor, does not take a map or trainer. 
	  public RunPokemon() {
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    setSize(256, 256 + 20); // +20 for title etc
	    
	    //Set location to middle of screen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	  	    
	    /***
	     * Instantiate the juicy, delicious objects
	     */
	    // which map?
	    Object[] possibleValues = { "Easy Map", "Hard Map" };
	    Object selectedValue = JOptionPane.showInputDialog(null,
	    "Which map do you want to play?", "Welcome to Pokeman",
	    JOptionPane.INFORMATION_MESSAGE, null,
	    possibleValues, possibleValues[0]);
	    if (selectedValue.equals("Easy Map")){
		    map = new Map();
	    }else if(selectedValue.equals("Hard Map")){
		    map = new Map("map2");
	    }else{
	    	System.exit(0);
	    } 
	    
	    // build trainer and map and battle
	    trainer = new Trainer("Testy");
	    mapPanel = new MapView(map);
	    battlePanel = new BattleView(new Scyther(), trainer); // scyther is a placeholder
	    
	    // listeners
	    this.addKeyListener(new ArrowKeyListener());
		this.addWindowListener(new CloseButtonListener());
		battlePanel.addKeyListener(new ArrowKeyListener());
		battlePanel.setFocusable(true); // gotta focus or cant listen
	    map.addObserver(mapPanel);
	    
	    // make the cardlayout bothViews, overworld is default
	    bothViews = new JPanel(new CardLayout());
	    bothViews.add(mapPanel, "overworld");
	    bothViews.add(battlePanel, "battle");
		bothViews.addKeyListener(new ArrowKeyListener());
	    bothViews.setFocusable(true);
	    this.add(bothViews, BorderLayout.CENTER);
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
	     * Instantiate the juicy, delicious objects given in parameter
	     */
	    // note: map can take map2 or map1
	    map = aMap; 
	    trainer = aTrainer;
	    mapPanel = new MapView(map);
	    battlePanel = new BattleView(new Scyther(), trainer); // scyther is a placeholder

	    // listeners
	    this.addKeyListener(new ArrowKeyListener());
		this.addWindowListener(new CloseButtonListener());
	    this.setFocusable(true);
		battlePanel.addKeyListener(new ArrowKeyListener());
		battlePanel.setFocusable(true); // gotta focus or cant listen
	    map.addObserver(mapPanel);
	    
	    // make the cardlayout bothViews, overworld is default
	    bothViews = new JPanel(new CardLayout());
	    bothViews.add(mapPanel, "overworld");
	    bothViews.add(battlePanel, "battle");
		bothViews.addKeyListener(new ArrowKeyListener());
	    bothViews.setFocusable(true);
	    this.add(bothViews, BorderLayout.CENTER);
	    
	  }

	  private class ArrowKeyListener implements KeyListener {
		
		long lastPressProcessed = 0;

	    @Override
	    public void keyPressed(KeyEvent ke) { 
 
	      // take inputs only 330ms at a time. 320ms to complete animation (I think.)
          if(System.currentTimeMillis() - lastPressProcessed > 330) {

	    	  if (ke.getKeyCode() == KeyEvent.VK_UP){
		    	  if (map.moveable("up")){
		    		  map.moveTrainer(Direction.NORTH);
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 100){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 100 steps");
		    			  System.exit(0);
		    		  }
		    		  // random chance to pokemon
		    		  if (map.beginPokemonBattle(map.getTrainerX(), map.getTrainerY()) == true){
		    			  // a wild pokemon will appear.
		    			  battlePanel = null;
		    			  battlePanel = new BattleView(map.whoToBattle(), trainer);
		    			  bothViews.add(battlePanel, "battle");
		    			  // switch view
		    			  CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		    			  cardLayout.show(bothViews, "battle");
		    			  // go back by hitting backspace. 
		    		  }
		    	  }
		      }
	    	  
		      if (ke.getKeyCode() == KeyEvent.VK_DOWN){
		    	  if (map.moveable("down")){
		    		  map.moveTrainer(Direction.SOUTH);
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 100){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 100 steps");
		    			  System.exit(0);
		    		  }
		    		  if (map.beginPokemonBattle(map.getTrainerX(), map.getTrainerY()) == true){
		    			  // a wild pokemon will appear.
		    			  battlePanel = null;
		    			  battlePanel = new BattleView(map.whoToBattle(), trainer);
		    			  bothViews.add(battlePanel, "battle");
		    			  // switch view
		    			  CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		    			  cardLayout.show(bothViews, "battle");
		    			  // go back by hitting backspace. 
		    		  }
		    	  }
		      }
	 
		      if (ke.getKeyCode() == KeyEvent.VK_LEFT){
		    	  if (map.moveable("left")){
		    		  map.moveTrainer(Direction.WEST);
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 100){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 100 steps");
		    			  System.exit(0);
		    		  }
		    		  if (map.beginPokemonBattle(map.getTrainerX(), map.getTrainerY()) == true){
		    			  // a wild pokemon will appear.
		    			  battlePanel = null;
		    			  battlePanel = new BattleView(map.whoToBattle(), trainer);
		    			  bothViews.add(battlePanel, "battle");
		    			  // switch view
		    			  CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		    			  cardLayout.show(bothViews, "battle");
		    			  // go back by hitting backspace. 
		    		  }
		    	  }	      
		      }
	
		      if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
		    	  if (map.moveable("right")){
		    		  map.moveTrainer(Direction.EAST);
		    		  trainer.incrementSteps(1);
		    		  if (trainer.getSteps() == 100){
		    			  JOptionPane.showMessageDialog(null, "Game Over. You walked 100 steps");
		    			  System.exit(0);
		    		  }
		    		  if (map.beginPokemonBattle(map.getTrainerX(), map.getTrainerY()) == true){
		    			  // a wild pokemon will appear.
		    			  battlePanel = null;
		    			  battlePanel = new BattleView(map.whoToBattle(), trainer);
		    			  bothViews.add(battlePanel, "battle");
		    			  // switch view
		    			  CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		    			  cardLayout.show(bothViews, "battle");
		    			  // go back by hitting backspace. 
		    		  }
		    	  }
		      }
		      
		      // check things - TODO: this is a stub
		      if (ke.getKeyCode() == KeyEvent.VK_ENTER){
		    	  Object[] possibleValues = { "Quit Game" };
		    	  Object selectedValue = JOptionPane.showInputDialog(null,
		    			  "<html><body><p>Steps Taken: " + trainer.getSteps() +
		    			  "</p><p><br />Pokemon Caught: " + trainer.getPokemonList() + 
		    			  "</p><p><br />Pokeballs Remaining: " + trainer.getItemList() +
		    			  "</p><br /></body></html>"
		    			  , "Menu",
		    	  JOptionPane.INFORMATION_MESSAGE, null,
		    	  possibleValues, possibleValues[0]);
		    	  if (selectedValue.equals("Quit Game")){
		    		  System.exit(0);
		    	  }
		      }
		      
		      // return to overworld. should require an event trigger
		      if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
		    	  CardLayout cardLayout = (CardLayout) bothViews.getLayout();
    			  cardLayout.show(bothViews, "overworld");
		      }
		      
              lastPressProcessed = System.currentTimeMillis();
		      
          }   
	    }

	    @Override
	    public void keyTyped(KeyEvent ke) { }
	    
	    @Override
	    public void keyReleased(KeyEvent ke) { }
	  }
	  
	  // serialization on window close via prompt
	  private class CloseButtonListener implements WindowListener{

			@Override
			public void windowClosing(WindowEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", null, JOptionPane.YES_NO_CANCEL_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					// write trainer
					File f = new File("trainerSave.txt");
					f.delete();
					try {
						FileOutputStream fos = new FileOutputStream(f, false);
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
						FileOutputStream fos = new FileOutputStream(f2, false);
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
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
			  
		  }
	  
	}
