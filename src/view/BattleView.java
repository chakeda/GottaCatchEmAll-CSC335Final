package view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Direction;
import model.Map;
import model.Pokemon;
import model.Tauros;
import model.Trainer;
import model.Scyther;
import model.Nidoran;

public class BattleView extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	/**
	 *  This is the Pokemon Encounter GUI
	 */
	  private Pokemon pokemon;
	  private Trainer trainer;
	  private JLabel battleLabel;
	  private JButton bait;
	  private JButton rock;
	  private JButton ball;
	  private boolean battleComplete;

	  // make a battle with the pokeman
	  public BattleView(Pokemon thePokemon, Trainer theTrainer) {
		  
		  this.pokemon = thePokemon;
		  this.trainer = theTrainer;
		  this.battleComplete = false;
		  
		  this.setLayout(new FlowLayout());
		  
		  battleLabel = new JLabel("Placeholder");
		  battleLabel.setText("Wild " + pokemon.getName() + " appeared!");
		  battleLabel.setFont(new Font("Courier", Font.BOLD, 18));
		  
		  bait = new JButton("Throw Bait");
		  bait.addActionListener(new BaitListener());
		  bait.setFocusable(false);
		  rock = new JButton("Throw Rock");
		  rock.addActionListener(new RockListener());
		  rock.setFocusable(false);
		  ball = new JButton("Throw Ball");
		  ball.addActionListener(new BallListener());
		  ball.setFocusable(false);
		  
	      this.add(battleLabel);
	      this.add(bait);
	      this.add(rock);
	      this.add(ball);
	  }
	  
	  // unused
	  public void setBattleComplete(boolean isComplete){
		  this.battleComplete = isComplete;
	  }
	  
	  // unused
	  public boolean getBattleComplete(){
		  return this.battleComplete;
	  }
	  
	  // throw bait
	  private class BaitListener implements ActionListener {
	      public void actionPerformed(ActionEvent evt) { 
	    	  pokemon.baitThrown();
			  battleLabel.setText(pokemon.getName() + " eats the bait!");
	    	  if (pokemon.willRunAway(new Random(0))){
				  battleLabel.setText(pokemon.getName() + " ran away!");
	    		  setBattleComplete(true);
	    	  }
	      }
	  }
	  
	  // throw rock
	  private class RockListener implements ActionListener {
	      public void actionPerformed(ActionEvent evt) { 
	    	  pokemon.rockThrown();
			  battleLabel.setText(pokemon.getName() + " is pissed!");
	    	  if (pokemon.willRunAway(new Random(0))){
				  battleLabel.setText(pokemon.getName() + " ran away!");
	    		  setBattleComplete(true);
	    	  }
	      }
	  }
	  
	  // throw ball
	  private class BallListener implements ActionListener {
	      public void actionPerformed(ActionEvent evt) { 
			  battleLabel.setText(pokemon.getName() + " bursts free from the ball!");
	    	  if (pokemon.isCaught(new Random(0))){
				  battleLabel.setText("You caught " + pokemon.getName() + "!");
	    		  trainer.addToPokemonList(pokemon);
	    		  setBattleComplete(true);
	    	  }
	      }
	  }

	@Override
	public void update(Observable probablyBattleClass, Object optional) {
		// TODO: stuff
	} 

}