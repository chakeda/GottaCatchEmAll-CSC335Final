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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Direction;
import model.Item;
import model.Map;
import model.Pokemon;
import model.Trainer;
import model.pokemon.Scyther;

// this file plays the game.

public class PlayerView extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private static final int NUMBER_OF_STEPS_ALLOWED = 500;


	private MapView mapPanel;
	private BattleView battlePanel;
	private JPanel bothViews;
	private Map map;
	private Trainer trainer;

	public PlayerView(Map aMap, Trainer aTrainer) {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(256, 256 + 20); // +20 for title etc

		// Set location to middle of screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		// set map and trainer
		if (aMap == null && aTrainer == null) {
			/*TODO adding in ability to choose win conditions: 
			 * 500 steps, catch MercerMaid, don't know what else?
			 */
			///// Begin new changes
			Object[] possibleValues1 = { "Traditional Safari Zone", "Catch the MercerMaid" };
			Object selectedValue1 = JOptionPane.showInputDialog(null, "Select Win Condition:",
					"Welcome to Pokeman", JOptionPane.INFORMATION_MESSAGE, null, possibleValues1, possibleValues1[0]);
			if(selectedValue1 == null) {
				System.exit(0);
			}
			else if (selectedValue1.equals("Traditional Safari Zone")) {
				//do something
			} else if (selectedValue1.equals("Catch the MercerMaid")) {
				//do something
			}
			
			///// End new changes
			Object[] possibleValues2 = { "Easy Map", "Hard Map" };
			Object selectedValue2 = JOptionPane.showInputDialog(null, "Which map do you want to play?",
					"Welcome to Pokeman", JOptionPane.INFORMATION_MESSAGE, null, possibleValues2, possibleValues2[0]);
			if(selectedValue2 == null) {
				System.exit(0);
			}
			else if (selectedValue2.equals("Easy Map")) {
				map = new Map();
			} else if (selectedValue2.equals("Hard Map")) {
				map = new Map("map2");
			}
			// build trainer and map and battle
			trainer = new Trainer("Testy");
			mapPanel = new MapView(map);
			battlePanel = new BattleView(new Scyther(), trainer);
		} 
		
		else {
			map = aMap;
			trainer = aTrainer;
			mapPanel = new MapView(aMap);
			battlePanel = new BattleView(new Scyther(), trainer);
		}
		
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

	private void checkSteps() {
		trainer.incrementSteps(1);
		if (trainer.getSteps() == NUMBER_OF_STEPS_ALLOWED) {
			JOptionPane.showMessageDialog(null, "<html><body><p>Game Over. You walked " + NUMBER_OF_STEPS_ALLOWED + " steps</p><br />"
		            + "<p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
					+ trainer.getPokemonList() + "</p><p><br />Items in Bag:"
					+ trainer.getItemList() + "</p><p><br />Pokeballs Remaining: "
					+ trainer.getPokeballsRemaining() + "</p><br /></body></html>");
			System.exit(0);
		}
	}
	
	private void checkBattle(){
		if (map.beginPokemonBattle(map.getTrainerY(), map.getTrainerX()) == true) {
			battlePanel = new BattleView(map.whoToBattle(), trainer);
			mapPanel.beginBattleAnimation();
			bothViews.add(battlePanel, "battle");
			CardLayout cardLayout = (CardLayout) bothViews.getLayout();
			cardLayout.show(bothViews, "battle");			
		}
	} 
	
	
	private void checkOutOfBalls(){
		if (trainer.getPokeballsRemaining() == 0) {
			JOptionPane.showMessageDialog(null, "<html><body><p>Game Over. You are out of pokeballs.</p><br />"
		            + "<p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
					+ trainer.getPokemonList() + "</p><p><br />Items in Bag:"
					+ trainer.getItemList() + "</p><p><br />Pokeballs Remaining: "
					+ trainer.getPokeballsRemaining() + "</p><br /></body></html>");
			System.exit(0);
		}
	}
	
	private void checkPokemonMaster(){
		// get distinct pokemons
		ArrayList<String> pokemons = trainer.getPokemonList();
		Set<String> uniquePokemons = new HashSet<String>();
		uniquePokemons.addAll(pokemons);
		pokemons.clear();
		pokemons.addAll(uniquePokemons);
		// caught all pokemons?
		if (pokemons.size() == 10) {
			JOptionPane.showMessageDialog(null, "<html><body><p>Congratulations! You caught all the pokemon.</p><br />"
		            + "<p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
					+ trainer.getPokemonList() + "</p><p><br />Items in Bag:"
					+ trainer.getItemList() + "</p><p><br />Pokeballs Remaining: "
					+ trainer.getPokeballsRemaining() + "</p><br /></body></html>");
			System.exit(0);
		}
	}
	
	private void checkItem(){
		Item i = map.getItemAt(map.getTrainerX(), map.getTrainerY());
		//System.out.println(map.getTrainerX() + " " + map.getTrainerY());
		if(i != null){
			JOptionPane.showMessageDialog(null, "You have found the " + i.getName() + " item! Press Enter to go to the menu to equip.");
			trainer.addToItemList(i);
			map.removeItemAt(map.getTrainerX(), map.getTrainerY());
		}
	}
	
	private void setMenuPossibleValues(){
		for(String i: trainer.getItemList()){
			if(i.equals("Running Shoes")){
				possibleValues[1] = "Equip Running Shoes";
			}
			else if(i.equals("Fishing Pole")){
				possibleValues[2] = "Use Fishing Pole";
			}
			else{
				possibleValues[3] = "Change Costume";
			}
		}
	}
	
	private void askToSave(){
		int reply = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", null,
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
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
		if (reply == JOptionPane.NO_OPTION) {
			// exit
			System.exit(0);
		}
	}
	
	Object[] possibleValues = { "Select Action...", "", "", "", "Quit Game"};
	private int processingSpeed = 330;
	 
	private class ArrowKeyListener implements KeyListener {

		long lastPressProcessed = 0;
		
		@Override
		public void keyPressed(KeyEvent ke) {

			// take inputs only 330ms at a time. 320ms to complete animation (I
			// think.)
			if (System.currentTimeMillis() - lastPressProcessed > processingSpeed) {

				if (ke.getKeyCode() == KeyEvent.VK_UP) {
					if (map.moveable("up")) {
						map.moveTrainer(Direction.NORTH);
						checkSteps();
						checkBattle();
						checkItem();
					}
				}

				else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					if (map.moveable("down")) {
						map.moveTrainer(Direction.SOUTH);
						checkSteps();
						checkBattle();
						checkItem();
					}
				}

				else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					if (map.moveable("left")) {
						map.moveTrainer(Direction.WEST);
						checkSteps();
						checkBattle();
						checkItem();
					}
					
				}

				else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (map.moveable("right")) {
						map.moveTrainer(Direction.EAST);
						checkSteps();
						checkBattle();
						checkItem();
					}
				}

				// check things - TODO: this is a stub
				else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					setMenuPossibleValues();
					Object selectedValue = JOptionPane.showInputDialog(null,
							"<html><body><p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
									+ trainer.getPokemonList() + "</p><p><br />Items in Bag:"
									+ trainer.getItemList() + "</p><p><br />Pokeballs Remaining: "
									+ trainer.getPokeballsRemaining() + "</p><br /></body></html>",
							"Menu", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);

					if (selectedValue!= null && selectedValue.equals("Quit Game")) {
						askToSave();
					}
					else if (selectedValue!= null && selectedValue.equals("Equip Running Shoes")) {
						mapPanel.speedUpTrainer();
						processingSpeed = 50;
					}
					else if (selectedValue!= null && selectedValue.equals("Change Costume")) {
						mapPanel.changeCostume();
					}
				}

				// return to overworld via backspace. should require an event trigger
				if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					CardLayout cardLayout = (CardLayout) bothViews.getLayout();
					cardLayout.show(bothViews, "overworld");
					checkPokemonMaster();
					checkOutOfBalls();
				}

				lastPressProcessed = System.currentTimeMillis();
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class CloseButtonListener implements WindowListener {

		@Override
		public void windowClosing(WindowEvent arg0) {
			askToSave();
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
		}

	}
}