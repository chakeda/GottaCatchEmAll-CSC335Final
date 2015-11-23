package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.Direction;
import model.Map;
import model.Pokemon;
import model.Trainer;
import model.pokemon.Nidoran;
import model.pokemon.Scyther;
import model.pokemon.Tauros;

public class BattleView extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * This is the Pokemon Encounter GUI
	 */
	private Pokemon pokemon;
	private Trainer trainer;
	private JTextArea battleLabel;
	private JButton bait;
	private JButton rock;
	private JButton ball;
	private JButton runAway;
	private JPanel buttons;
	private JTextArea exitScreen;
	private boolean battleComplete;
	private Image chansey, cubone, kangaskhan, nidoran, paras, pinsir, rhyhorn, scyther, tauros, venomoth;
	private Image pokemonImage;
	
	// make a battle with the pokeman
	public BattleView(Pokemon thePokemon, Trainer theTrainer) {
		this.pokemon = thePokemon;
		this.trainer = theTrainer;
		
	    try {
	      chansey = ImageIO.read(new File("./images/pokemonImages/chansey.png"));
	      cubone = ImageIO.read(new File("./images/pokemonImages/cubone.png"));
	      kangaskhan = ImageIO.read(new File("./images/pokemonImages/kangaskhan.png"));
	      nidoran = ImageIO.read(new File("./images/pokemonImages/nidoran.png"));
	      paras = ImageIO.read(new File("./images/pokemonImages/paras.png"));
	      pinsir = ImageIO.read(new File("./images/pokemonImages/pinsir.png"));
	      rhyhorn = ImageIO.read(new File("./images/pokemonImages/rhyhorn.png"));
	      scyther = ImageIO.read(new File("./images/pokemonImages/scyther.png"));
	      tauros = ImageIO.read(new File("./images/pokemonImages/tauros.png"));
	      venomoth = ImageIO.read(new File("./images/pokemonImages/venomoth.png"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    if (thePokemon.getName().equals("Chansey")){
	    	pokemonImage = chansey;
	    }
	    if (thePokemon.getName().equals("Cubone")){
	    	pokemonImage = cubone;
	    }
	    if (thePokemon.getName().equals("Kangaskhan")){
	    	pokemonImage = kangaskhan;
	    }
	    if (thePokemon.getName().equals("Nidoran")){
	    	pokemonImage = nidoran;
	    }
	    if (thePokemon.getName().equals("Paras")){
	    	pokemonImage = paras;
	    }
	    if (thePokemon.getName().equals("Pinsir")){
	    	pokemonImage = pinsir;
	    }
	    if (thePokemon.getName().equals("Rhyhorn")){
	    	pokemonImage = rhyhorn;
	    }
	    if (thePokemon.getName().equals("Scyther")){
	    	pokemonImage = scyther;
	    }
	    if (thePokemon.getName().equals("Tauros")){
	    	pokemonImage = tauros;
	    }
	    if (thePokemon.getName().equals("Venomoth")){
	    	pokemonImage = venomoth;
	    }

		battleLabel = new JTextArea("Placeholder");
		battleLabel.setText("Wild " + pokemon.getName() + " appeared!");
		battleLabel.setFont(new Font("Courier", Font.BOLD, 16));
		battleLabel.setBackground(getBackground());
		
		JLabel pokemonImageLabel = new JLabel(new ImageIcon(pokemonImage));
		this.add(pokemonImageLabel);

		bait = new JButton("Throw Bait");
		bait.addActionListener(new BaitListener()); 
		bait.setFocusable(false);
		rock = new JButton("Throw Rock");
		rock.addActionListener(new RockListener());
		rock.setFocusable(false);
		ball = new JButton("Throw Ball");
		ball.addActionListener(new BallListener());
		ball.setFocusable(false);
		runAway = new JButton("Run Away");
		runAway.addActionListener(new runAwayListener());
		runAway.setFocusable(false);

		this.add(battleLabel);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,2));
		buttons.add(bait);
		buttons.add(rock);
		buttons.add(ball);
		buttons.add(runAway);
		
		this.add(buttons);
		exitScreen = new JTextArea("");
		exitScreen.setBackground(getBackground());
		this.add(exitScreen);
		

		this.battleComplete = false;
	}

	// unused
	public void setBattleComplete() {
		this.battleComplete = true;
		exitScreen.setText("Press BackSpace to exit.");
	}

	// unused
	public boolean getBattleComplete() {
		return this.battleComplete;
	}

	// throw bait
	private class BaitListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				pokemon.baitThrown();
				battleLabel.setText(pokemon.getName() + " eats the bait!");
				if (pokemon.willRunAway(new Random(0))) {
					battleLabel.setText(pokemon.getName() + " ran away!");
					setBattleComplete();
				}
			}
		}
	}

	// throw rock
	private class RockListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				pokemon.rockThrown();
				battleLabel.setText(pokemon.getName() + " is pissed!");
				if (pokemon.willRunAway(new Random(0))) {
					battleLabel.setText(pokemon.getName() + " ran away!");
					setBattleComplete();
				}
			}
		}
	}

	// throw ball
	private class BallListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				battleLabel.setText(pokemon.getName() + " bursts free!");
				if (pokemon.isCaught(new Random(0))) {
					battleLabel.setText("You caught " + pokemon.getName() + "!");
					trainer.addToPokemonList(pokemon);
					setBattleComplete();
				}
			}
		}
	}
	
	private class runAwayListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				battleLabel.setText("Ran away safely!");
				setBattleComplete();
			}
		}
	}

}