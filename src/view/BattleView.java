package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import controller.SongPlayer;
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
	private Image pokemonImage;
	
	private int projectileX, projectileY, tic, n;
	private Timer timer;
	private Color projectileColor;
	private SongPlayer songplayer;
	
	// make a battle with the pokeman
	public BattleView(Pokemon thePokemon, Trainer theTrainer, SongPlayer sp) {
		this.pokemon = thePokemon;
		this.trainer = theTrainer;
	    timer = new Timer(40, new ProjectileListener());
		
		String pokemonFileName = thePokemon.getName().toLowerCase() + ".png";
		
	    try {
	      pokemonImage = ImageIO.read(new File("./images/pokemonImages/" + pokemonFileName));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		battleLabel = new JTextArea("Placeholder");
		battleLabel.setText("Wild " + pokemon.getName() + " appeared!");
		battleLabel.setFont(new Font("Courier", Font.BOLD, 14));
		battleLabel.setBackground(getBackground());
		
		// poke image
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
		
		projectileY = 500; // off screen at first

		this.battleComplete = false;
		this.songplayer = sp;
		
	}
	
	/************
	 * Projectile Animation Methods
	 ************/
	
	// launch projectile
	private void drawProjectileWithAnimation() {
		// set projectile initial position
		projectileX = 0;
		projectileY = 35;
		n = 10; 
		tic = 1;
		timer.start();
	}
	
	// projectile animation
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(projectileColor);
		Ellipse2D.Double oval = new Ellipse2D.Double(
				projectileX, projectileY, 10, 10);
		g2.fill(oval);
		
	}
	
	// move around
    private class ProjectileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tic <= n){
				projectileX = projectileX + 5;
				repaint();
				tic++;
			}else{
				timer.stop();
			}
			
		}
	}
    
	/************
	 * Messages
	 ************/

	public void setBattleComplete() {
		this.battleComplete = true;
		exitScreen.setText("Press BackSpace to exit.");
	}

	public boolean getBattleComplete() {
		return this.battleComplete;
	}
	
	/************
	 * Button Listeners
	 ************/

	// throw bait
	private class BaitListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				projectileColor = Color.PINK;
				drawProjectileWithAnimation();
				pokemon.baitThrown();
				battleLabel.setText(pokemon.getName() + " eats the bait!");
				if (pokemon.willRunAway(new Random())) {
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
				projectileColor = Color.GRAY;
				drawProjectileWithAnimation();
				pokemon.rockThrown();
				battleLabel.setText(pokemon.getName() + " is pissed!");
				if (pokemon.willRunAway(new Random())) {
					battleLabel.setText(pokemon.getName() + " ran away!");
					setBattleComplete();
				}
			}
		}
	}

	// throw ball
	Timer t;
	private class BallListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (!battleComplete) {
				projectileColor = Color.GREEN;
				drawProjectileWithAnimation();
				trainer.throwPokeball();
				if (pokemon.isCaught(new Random())) {
					// play congrats
					songplayer.playCongratsMusic();
					battleLabel.setText("You caught " + pokemon.getName() + "!");
					trainer.addToPokemonList(pokemon);
					setBattleComplete();
				}
				else{
					battleLabel.setText(pokemon.getName() + " bursts free!");
				}
			}
		}
	}
	
	private class runAwayListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			//drawProjectileWithAnimation();
			if (!battleComplete) {
				battleLabel.setText("Ran away safely!");
				setBattleComplete();
			}
		}
	}

}