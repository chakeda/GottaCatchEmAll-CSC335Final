package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.SongPlayer;
import model.Direction;
import model.Item;
import model.Map;
import model.Pokemon;
import model.Trainer;
import model.pokemon.MercerMermaid;
import model.pokemon.Scyther;

// this file plays the game.

public class PlayerView extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private static int NUMBER_OF_STEPS_ALLOWED = 500;

	private MapView mapPanel;
	private BattleView battlePanel;
	private ItemView itemView; 
	private JPanel bothViews;
	private Map map;
	private Trainer trainer;
	private SongPlayer songplayer;
	
	private Pokemon test;
	private boolean isEndlessMode;
	

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
			//select win condition
			Object[] possibleValues1 = { "Traditional", "Endless Mode" };
			Object selectedValue1 = JOptionPane.showInputDialog(null, "Select your win condition: ",
					"Welcome to Pokeman", JOptionPane.INFORMATION_MESSAGE, null, possibleValues1, possibleValues1[0]);
			if (selectedValue1.equals("Traditional")) {
				isEndlessMode = false;
			} else if (selectedValue1.equals("Endless Mode")) {
				isEndlessMode = true;
				NUMBER_OF_STEPS_ALLOWED = Integer.MAX_VALUE;
			} else {
				System.exit(0);
			}

			// select map
			Object[] possibleValues2 = { "Easy Map", "Hard Map" };
			Object selectedValue2 = JOptionPane.showInputDialog(null, "Which map do you want to play?",
					"Welcome to Pokeman", JOptionPane.INFORMATION_MESSAGE, null, possibleValues2, possibleValues2[0]);
			if (selectedValue2.equals("Easy Map")) {
				map = new Map();
			} else if (selectedValue2.equals("Hard Map")) {
				map = new Map("map2");
			} else {
				System.exit(0);
			}
			// build trainer and map and battle
			trainer = new Trainer("Testy");
			mapPanel = new MapView(map);
			battlePanel = new BattleView(new Scyther(), trainer, songplayer, this);
		} else {
			map = aMap;
			trainer = aTrainer;
			mapPanel = new MapView(aMap);
			battlePanel = new BattleView(new Scyther(), trainer, songplayer, this);
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

		songplayer = new SongPlayer();;

		songplayer.playMainMusic();

	}

	private void checkSteps() {
		trainer.incrementSteps(1);
		if (trainer.getSteps() == NUMBER_OF_STEPS_ALLOWED) {
			JOptionPane.showMessageDialog(null,
					"<html><body><p>Game Over. You walked " + NUMBER_OF_STEPS_ALLOWED + " steps</p><br />"
							+ "<p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
							+ trainer.getPokemonList() + "</p><p><br />Items in Bag:" + trainer.getItemList()
							+ "</p><p><br />Pokeballs Remaining: " + trainer.getPokeballsRemaining()
							+ "</p><br /></body></html>");
			System.exit(0);
		}
	}

	private void checkBattle() {
		if (map.beginPokemonBattle(map.getTrainerY(), map.getTrainerX())) {
			lockKeyPad();
			songplayer.playBattleMusic();
			mapPanel.beginBattleAnimation(this);
		}
	}
	
	// battle with mercer. unfortunately the animation is not used
	private void forceBattleWithMercer() {
		
		Pokemon mercerMermaid = new MercerMermaid();
		lockKeyPad();
		songplayer.playBattleMusic();
		beginBattle(mercerMermaid);
	}

	
	public void beginBattle(Pokemon optionalPokemon){
		if (optionalPokemon.getName().equals("Pinsir")){
			// Pinsir is the filler pokemon. Get the random pokemon from the map
			battlePanel = new BattleView(map.whoToBattle(), trainer, songplayer, this);
		}else{
			// a specified pokemon battle begins
			battlePanel = new BattleView(optionalPokemon, trainer, songplayer, this);
		}
		bothViews.add(battlePanel, "battle");
		CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		cardLayout.show(bothViews, "battle");	
	}

	private boolean keyPadLocked = false;
	
	public void lockKeyPad(){
		keyPadLocked = true;
	}
	public void unLockKeyPad(){
		keyPadLocked = false;
	}

	private void checkOutOfBalls() {
		if (trainer.getPokeballsRemaining() == 0) {
			JOptionPane.showMessageDialog(null,
					"<html><body><p>Game Over. You are out of pokeballs.</p><br />" + "<p>Steps Taken: "
							+ trainer.getSteps() + "</p><p><br />Pokemon Caught: " + trainer.getPokemonList()
							+ "</p><p><br />Items in Bag:" + trainer.getItemList()
							+ "</p><p><br />Pokeballs Remaining: " + trainer.getPokeballsRemaining()
							+ "</p><br /></body></html>");
			System.exit(0);
		}
	}

	private void checkPokemonMaster() {
		// get distinct pokemons
		ArrayList<String> pokemons = trainer.getPokemonList();
		Set<String> uniquePokemons = new HashSet<String>();
		uniquePokemons.addAll(pokemons);
		pokemons.clear();
		pokemons.addAll(uniquePokemons);
		// caught all pokemons?
		if (pokemons.size() == 10) {
			JOptionPane.showMessageDialog(null,
					"<html><body><p>Congratulations! You caught all the pokemon.</p><br />" + "<p>Steps Taken: "
							+ trainer.getSteps() + "</p><p><br />Pokemon Caught: " + trainer.getPokemonList()
							+ "</p><p><br />Items in Bag:" + trainer.getItemList()
							+ "</p><p><br />Pokeballs Remaining: " + trainer.getPokeballsRemaining()
							+ "</p><br /></body></html>");
			System.exit(0);
		}
	}

	private void checkItem() {
		Item i = map.getItemAt(map.getTrainerY(), map.getTrainerX());
		// System.out.println(map.getTrainerX() + " " + map.getTrainerY());
		if (i != null) {
			// play congrats
			songplayer.playCongratsMusic();
			JOptionPane.showMessageDialog(null,
					"You have found the " + i.getName() + " item! Press Enter to go to the menu to equip.");
			trainer.addToItemList(i);
			map.removeItemAt(map.getTrainerX(), map.getTrainerY());
			songplayer.playMainMusic();
		}
	}

	private void setMenuPossibleValues() {
		for (String i : trainer.getItemList()) {
			if (i.equals("Running Shoes")) {
				possibleValues[1] = "Equip Running Shoes";
			} else if (i.equals("Fishing Pole")) {
				possibleValues[2] = "Use Fishing Pole";
			}
			else if(i.equals("Costume Change")){
				possibleValues[3] = "Change Costume";
			}
			else if(i.equals("Lemonade")){
				possibleValues[4] = "Use Lemonade";
			}
			else if(i.equals("Energy")){
				possibleValues[5] = "Use Energy Root";
			}
			else if(i.equals("Berry Juice")){
				possibleValues[6] = "Use Berry Juice";
			}
			else if(i.equals("Water")){
				possibleValues[7] = "Use Fresh Water";
			}
		}
	}
	Object[] possiblePokemon = {"","","","","","","","","",""};
	Object selectedPokemon; 
	private void setPossiblePokemonValues(){
		for(int i = 0; i < trainer.getPokemonList().size(); i++){
			possiblePokemon[i] = trainer.getPokemonList().get(i);
		}
	}
	
	//Update the HP of a pokemon that used one of the 4 Items
	private void setNewHPLemonade(){
		Pokemon test; 
		for(int i = 0; i < trainer.getAllPokemon().size(); i++){
			if(selectedPokemon.equals(trainer.getAllPokemon().get(i).getName())){
				test = trainer.getAllPokemon().get(i);
				//System.out.println(test.getHP());
				test.updateHP(80);
				//System.out.println(test.getHP());
			}
		}
	}
	private void setNewHPWater(){
		Pokemon test; 
		for(int i = 0; i < trainer.getAllPokemon().size(); i++){
			if(selectedPokemon.equals(trainer.getAllPokemon().get(i).getName())){
				test = trainer.getAllPokemon().get(i);
				//System.out.println(test.getHP());
				test.updateHP(50);
				//System.out.println(test.getHP());
			}
		}
	}
	private void setNewHPBerry(){
		Pokemon test; 
		for(int i = 0; i < trainer.getAllPokemon().size(); i++){
			if(selectedPokemon.equals(trainer.getAllPokemon().get(i).getName())){
				test = trainer.getAllPokemon().get(i);
				//System.out.println(test.getHP());
				test.updateHP(20);
				//System.out.println(test.getHP());
			}
		}
	}
	private void setNewHPEnergy(){
		Pokemon test; 
		for(int i = 0; i < trainer.getAllPokemon().size(); i++){
			if(selectedPokemon.equals(trainer.getAllPokemon().get(i).getName())){
				test = trainer.getAllPokemon().get(i);
				//System.out.println(test.getHP());
				test.updateHP(200);
				//System.out.println(test.getHP());
			}
		}
	}

	private void askToSave() {
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
	
	private Object[] possibleValues = { "Select Action...", "", "", "", "", "", "", "", "Quit Game" };
	private int processingSpeed = 330;

	private class ArrowKeyListener implements KeyListener {

		long lastPressProcessed = 0;

		@Override
		public void keyPressed(KeyEvent ke) {

			// take inputs only 330ms at a time. 320ms to complete animation (I
			// think.)
			if ((System.currentTimeMillis() - lastPressProcessed) > processingSpeed && !keyPadLocked) {

				
				if (ke.getKeyCode() == KeyEvent.VK_UP) {
					if (map.moveable(Direction.NORTH)!=null) {
						map.moveTrainer(Direction.NORTH);
						if (isEndlessMode){
							checkPokemonMaster();
						}else{
							checkSteps();
						}
						checkItem();
						checkBattle();
					}
				}

				else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					if (map.moveable(Direction.SOUTH)!=null) {
						map.moveTrainer(Direction.SOUTH);
						if (isEndlessMode){
							checkPokemonMaster();
						}else{
							checkSteps();
						}
						checkItem();
						checkBattle();
					}
				}

				else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					if (map.moveable(Direction.WEST)!=null) {
						map.moveTrainer(Direction.WEST);	
						if (isEndlessMode){
							checkPokemonMaster();
						}else{
							checkSteps();
						}
						checkItem();
						checkBattle();
					}

				}

				else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (map.moveable(Direction.EAST)!=null) {
						map.moveTrainer(Direction.EAST);
						if (isEndlessMode){
							checkPokemonMaster();
						}else{
							checkSteps();
						}
						checkItem();
						checkBattle();
					}
				}

				// menu 
				else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					setMenuPossibleValues();
					Object selectedValue = JOptionPane.showInputDialog(null,
							"<html><body><p>Steps Taken: " + trainer.getSteps() + "</p><p><br />Pokemon Caught: "
									+ trainer.getPokemonList() + "</p><p><br />Items in Bag:" + trainer.getItemList()
									+ "</p><p><br />Pokeballs Remaining: " + trainer.getPokeballsRemaining()
									+ "</p><br /></body></html>",
							"Menu", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);

					if (selectedValue != null && selectedValue.equals("Quit Game")) {
						askToSave();
					} else if (selectedValue != null && selectedValue.equals("Equip Running Shoes")) {
						mapPanel.speedUpTrainer();
						processingSpeed = 50;
					} else if (selectedValue != null && selectedValue.equals("Use Fishing Pole")) {
						// adjacent tile is water? cast thy fishing pole
						if (map.getTileAt(map.getTrainerY()+1, map.getTrainerX()).equals("W")
								|| map.getTileAt(map.getTrainerY()-1, map.getTrainerX()).equals("W")
								|| map.getTileAt(map.getTrainerY(), map.getTrainerX()+1).equals("W")
								|| map.getTileAt(map.getTrainerY(), map.getTrainerX()-1).equals("W")
								){
							mapPanel.changeCostumeFishing();

							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							forceBattleWithMercer();
							
							mapPanel.resetFishChange();
						}else{
							JOptionPane.showMessageDialog(null,
									"Professor Mercer's words... There is a time and place and design principle for everything, laddie!");
						}
						// wow factor is here
					} else if (selectedValue != null && selectedValue.equals("Change Costume")) {
						mapPanel.changeCostume();
					} else if (selectedValue != null && selectedValue.equals("Quit Game")) {
						System.exit(0);
					}
					//added a little bit of "WOW" place image next to the items that increase HP
					else if (selectedValue != null && selectedValue.equals("Use Lemonade")){
						setPossiblePokemonValues();
						Image theImage = null;
						try {
							theImage = ImageIO.read(new File("./images/lemon.jpg"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ImageIcon image = new ImageIcon(theImage);
						selectedPokemon = JOptionPane.showInputDialog(null, "Please select a Pokemon", "Pokemon", 
								JOptionPane.QUESTION_MESSAGE, image, possiblePokemon, possiblePokemon[0]);
						setNewHPLemonade();
						itemView = new ItemView((String) selectedPokemon, "Lemonade");
					}
					else if (selectedValue != null && selectedValue.equals("Use Energy Root")){
						setPossiblePokemonValues();
						Image theImage = null;
						try {
							theImage = ImageIO.read(new File("./images/EnergyRoot.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ImageIcon image = new ImageIcon(theImage);
						selectedPokemon = JOptionPane.showInputDialog(null, "Please select a Pokemon", "Pokemon", 
								JOptionPane.QUESTION_MESSAGE, image, possiblePokemon, possiblePokemon[0]);
						setNewHPEnergy();
						itemView = new ItemView((String) selectedPokemon, "Energy Root");
					}
					else if (selectedValue != null && selectedValue.equals("Use Berry Juice")){
						setPossiblePokemonValues();
						Image theImage = null;
						try {
							theImage = ImageIO.read(new File("./images/BerryJuice.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ImageIcon image = new ImageIcon(theImage);
						selectedPokemon = JOptionPane.showInputDialog(null, "Please select a Pokemon", "Pokemon", 
								JOptionPane.QUESTION_MESSAGE, image, possiblePokemon, possiblePokemon[0]);
						
						setNewHPBerry();
						itemView = new ItemView((String) selectedPokemon, "Berry Juice");
					}
					else if (selectedValue != null && selectedValue.equals("Use Fresh Water")){
						setPossiblePokemonValues();
						Image theImage = null;
						try {
							theImage = ImageIO.read(new File("./images/FreshWater.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ImageIcon image = new ImageIcon(theImage);
						selectedPokemon = JOptionPane.showInputDialog(null, "Please select a Pokemon", "Pokemon", 
								JOptionPane.QUESTION_MESSAGE, image, possiblePokemon, possiblePokemon[0]);
						setNewHPWater();
						itemView = new ItemView((String) selectedPokemon, "Fresh Water");
					}
				}
					

				// return to overworld via backspace. should require an event
				// trigger
				if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					battlePanel.fadeOut();
				}
				lastPressProcessed = System.currentTimeMillis();
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

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
	
	public void switchToOverworld(){
		CardLayout cardLayout = (CardLayout) bothViews.getLayout();
		cardLayout.show(bothViews, "overworld");
		songplayer.playMainMusic();
		checkPokemonMaster();
		checkOutOfBalls();
	}
}
