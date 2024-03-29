package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Category;
import model.Direction;
import model.Trainer;
import model.items.FishingPole;
import model.pokemon.Scyther;

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
		assertEquals(trainer.getPokemonList().get(0), s.getName());
		assertEquals(trainer.getPokemonCaught(), 1);
		
		FishingPole fp = new FishingPole("fishPole", Category.HOLD_ITEM);
		trainer.addToItemList(fp);
		assertEquals(trainer.getItemList().size(), 1);
		assertEquals(trainer.getItemList().get(0), fp.getName());
		trainer.setItemUsing(fp);
		assertEquals(fp, trainer.getItemUsing());
		
		trainer.setItemUsing(null);
		assertEquals(trainer.getItemUsing(), null);
		
		
		//**********pokeball testing*********
		trainer.throwPokeball();
		assertEquals(trainer.getPokeballsRemaining(), 14);
		
		
		//*****steps test
		assertTrue(trainer.gameOver()==false);	
		for(int i=0; i<49; i++){
			trainer.incrementSteps(1);
		}
		assertTrue(trainer.gameOver()==false);	
	}
}
