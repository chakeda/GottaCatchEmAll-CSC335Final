package view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Icon;
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
	private boolean battleComplete, pokeballToss;

	private JPanel imagePanel;
	private Image pokemonImage, trainerImage, trainerImage2, pokeball;
	private JLabel pokemonImageLabel, trainerImageLabel;

	private int projectileX, projectileY, tic, n;
	private Timer timer, ballTimer;
	private Color projectileColor;
	private SongPlayer songplayer;
	private PlayerView pv;

	// make a battle with the pokemon
	public BattleView(Pokemon thePokemon, Trainer theTrainer, SongPlayer sp, PlayerView pv) {
		this.pokemon = thePokemon;
		this.trainer = theTrainer;
		this.pv = pv;
		timer = new Timer(40, new ProjectileListener());
		ballTimer = new Timer(40, new ProjectileListener());

		String pokemonFileName = thePokemon.getName().toLowerCase() + ".png";

		try {
			pokemonImage = ImageIO.read(new File("./images/pokemonImages/" + pokemonFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			trainerImage = ImageIO.read(new File("./images/trainerBattleImages/trainerNeutral.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			trainerImage2 = ImageIO.read(new File("./images/trainerBattleImages/trainerThrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			pokeball = ImageIO.read(new File("./images/pokeball.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		battleLabel = new JTextArea("Placeholder");
		battleLabel.setText("Wild " + pokemon.getName() + " appeared!");
		battleLabel.setFont(new Font("Courier", Font.BOLD, 14));
		battleLabel.setBackground(getBackground());

		// trainer image
		trainerImageLabel = new JLabel(new ImageIcon(trainerImage));
		this.add(trainerImageLabel);

		// poke image
		pokemonImageLabel = new JLabel(new ImageIcon(pokemonImage));
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
		buttons.setLayout(new GridLayout(2, 2));
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

		// initial slide in anime
		ActionListener initialAnimationPerformer = new ActionListener() {
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count == 0) {
					pokemonImageLabel.setLocation(230, pokemonImageLabel.getLocation().y);
					trainerImageLabel.setLocation(-30, trainerImageLabel.getLocation().y);
				}
				if (count == 20) {
					((Timer) e.getSource()).stop();
				} else {
					// and down
					pokemonImageLabel.setLocation(pokemonImageLabel.getLocation().x - 5,
							pokemonImageLabel.getLocation().y);
					trainerImageLabel.setLocation(trainerImageLabel.getLocation().x + 5,
							trainerImageLabel.getLocation().y);
					count++;
				}
			}
		};
		new Timer(100, initialAnimationPerformer).start();

	}

	/************
	 * Projectile Animation Methods
	 ************/

	// launch projectile
	private void drawProjectileWithAnimation() {
		// set projectile initial position
		pokeballToss = false;
		projectileX = 100;
		projectileY = 35;
		n = 10;
		tic = 1;
		timer.start();
	}

	private void drawBallWithAnimation() {
		pokeballToss = true;
		projectileX = 100;
		projectileY = 35;
		n = 10;
		tic = 1;
		ballTimer.start();
	}

	// projectile animation
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (pokeballToss) {
			g2.drawImage(pokeball, projectileX, projectileY, null);
		} else {
			g2.setColor(projectileColor);
			Ellipse2D.Double oval = new Ellipse2D.Double(projectileX, projectileY, 10, 10);
			g2.fill(oval);
		}

	}

	// move around
	private class ProjectileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tic <= n) {
				projectileX = projectileX + 5;
				repaint();
				tic++;
			} else {
				timer.stop();
			}

		}
	}

	/************
	 * Messages
	 ************/

	public void setBattleComplete() {
		this.battleComplete = true;
		pv.unLockKeyPad();
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

				// animate pokemon
				ActionListener animationPerformer = new ActionListener() {
					int count = 0;

					@Override
					public void actionPerformed(ActionEvent e) {

						// trainer throws
						Icon trainerImage2Icon = new ImageIcon(trainerImage2);
						trainerImageLabel.setIcon(trainerImage2Icon);

						if (count == 10) {
							// trainer normal
							Icon trainerImageIcon = new ImageIcon(trainerImage);
							trainerImageLabel.setIcon(trainerImageIcon);
							((Timer) e.getSource()).stop();
						} else if (count % 2 == 0) {
							// up
							pokemonImageLabel.setLocation(pokemonImageLabel.getLocation().x,
									pokemonImageLabel.getLocation().y + 5);
							count++;
						} else {
							// and down
							pokemonImageLabel.setLocation(pokemonImageLabel.getLocation().x,
									pokemonImageLabel.getLocation().y - 5);
							count++;
						}
					}
				};
				new Timer(100, animationPerformer).start();

				pokemon.baitThrown();
				battleLabel.setText("     " + pokemon.getName() + " eats the bait!     ");
				if (pokemon.willRunAway(new Random())) {
					battleLabel.setText("     " + pokemon.getName() + " ran away!     ");
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

				// animate pokemon
				ActionListener animationPerformer = new ActionListener() {
					int count = 0;

					@Override
					public void actionPerformed(ActionEvent e) {

						// trainer throws
						Icon trainerImage2Icon = new ImageIcon(trainerImage2);
						trainerImageLabel.setIcon(trainerImage2Icon);

						if (count == 10) {
							// trainer normal
							Icon trainerImageIcon = new ImageIcon(trainerImage);
							trainerImageLabel.setIcon(trainerImageIcon);
							((Timer) e.getSource()).stop();
						} else if (count % 2 == 0) {
							// left
							pokemonImageLabel.setLocation(pokemonImageLabel.getLocation().x + 5,
									pokemonImageLabel.getLocation().y);
							count++;
						} else {
							// and right
							pokemonImageLabel.setLocation(pokemonImageLabel.getLocation().x - 5,
									pokemonImageLabel.getLocation().y);
							count++;
						}
					}
				};
				new Timer(100, animationPerformer).start();

				pokemon.rockThrown();
				battleLabel.setText("     " + pokemon.getName() + " is pissed!     ");
				if (pokemon.willRunAway(new Random())) {
					battleLabel.setText("     " + pokemon.getName() + " ran away!     ");
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
				drawBallWithAnimation();

				// animate pokemon
				ActionListener animationPerformer = new ActionListener() {
					int count = 0;

					@Override
					public void actionPerformed(ActionEvent e) {

						// trainer throws
						Icon trainerImage2Icon = new ImageIcon(trainerImage2);
						trainerImageLabel.setIcon(trainerImage2Icon);

						if (count == 10) {
							// trainer normal
							Icon trainerImageIcon = new ImageIcon(trainerImage);
							trainerImageLabel.setIcon(trainerImageIcon);
							((Timer) e.getSource()).stop();
						} else {
							// turn pokemon to ball
							// Icon pokemonBallImageIcon = new
							// ImageIcon(pokeball);
							// pokemonImageLabel.setIcon(pokemonBallImageIcon);

							count++;
						}
					}
				};

				ActionListener caught = new ActionListener() {
					int count = 0;

					@Override
					public void actionPerformed(ActionEvent e) {
						if (count == 4) {
							// Icon pokemonBallImageIcon = new
							// ImageIcon(pokeball);
							pokemonImageLabel.setIcon(null);
							((Timer) e.getSource()).stop();
						}
						count++;
					}
				};

				ActionListener burstFree = new ActionListener() {
					int count = 0;

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (count == 4) {
							Icon pokemonImageIcon = new ImageIcon(pokemonImage);
							pokemonImageLabel.setIcon(pokemonImageIcon);
							((Timer) e.getSource()).stop();
						}
						count++;
					}
				};

				// new Timer(100, animationPerformer).start();

				trainer.throwPokeball();
				if (pokemon.isCaught(new Random())) {

					// TODO: keep the pokemon in a ball state
					// somehow this doesnt work... it gets called when not
					// supposed to

					// Icon pokemonBallImageIcon = new ImageIcon(pokeball);
					// pokemonImageLabel.setIcon(pokemonBallImageIcon);
					new Timer(100, caught).start();
					songplayer.playPokemonCaughtMusic();
					battleLabel.setText("     You caught " + pokemon.getName() + "!     ");
					trainer.addToPokemonList(pokemon);
					setBattleComplete();
				} else {
					battleLabel.setText("     " + pokemon.getName() + " bursts free!     ");
					pokemonImageLabel.setLocation(130, pokemonImageLabel.getLocation().y);
					new Timer(100, burstFree).start();
				}
			}
		}
	}

	private class runAwayListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			// drawProjectileWithAnimation();
			if (!battleComplete) {
				battleLabel.setText("     Ran away safely!     ");
				setBattleComplete();
			}
		}
	}

}