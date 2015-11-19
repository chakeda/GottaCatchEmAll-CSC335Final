package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Category;
import model.Direction;
import model.FishingPole;
import model.Scyther;
import model.Trainer;

public class TrainerTest {
	@Test
	public void test() {
		
		Trainer trainer = new Trainer("Ash");
		assertEquals("Ash", trainer.getPlayerName());
		
		//****Initialization tests
		assertEquals(trainer.getSteps(),0);
		assertEquals(trainer.getPokemonCaught(),0);
		assertEquals(trainer.getDirectionFacing(), Direction.NORTH);
		assertEquals(trainer.getPokeballsRemaining(), 15);
		assertEquals(trainer.getItemList().size(), 0);
		assertEquals(trainer.getPokemonList().size(), 0);
		assertEquals(trainer.getItemUsing(), null);
		
		
		//*******Adding pokemon and Items****
		Scyther s = new Scyther();
		trainer.addToPokemonList(s);
		assertEquals(trainer.getPokemonList().size(), 1);
		assertEquals(trainer.getPokemonList().get(0), s);
		assertEquals(trainer.getPokemonCaught(), 1);
		
		FishingPole fp = new FishingPole("fishPole", Category.HOLD_ITEM);
		trainer.addToItemList(fp);
		assertEquals(trainer.getItemList().size(), 1);
		assertEquals(trainer.getItemList().get(0), fp);
		trainer.setItemUsing(fp);
		assertEquals(fp, trainer.getItemUsing());
		
		trainer.setItemUsing(null);
		assertEquals(trainer.getItemUsing(), null);
		
		
		//**********pokeball testing*********
		trainer.throwPokeball();
		assertEquals(trainer.getPokeballsRemaining(), 14);
		
		
		//*****steps test
		assertTrue(trainer.gameOver()==false);	
		for(int i=0; i<50; i++){
			trainer.incrementSteps(1);
		}
		assertTrue(trainer.gameOver()==true);	
	}
}
