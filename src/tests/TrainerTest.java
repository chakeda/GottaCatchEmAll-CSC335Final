package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		
		//****Initialization tests
		assertEquals(trainer.getSteps(),0);
		assertEquals(trainer.getPokemonCaught(),0);
		assertEquals(trainer.getDirectionFacing(), Direction.NORTH);
		assertEquals(trainer.getPokeballsRemaining(), 15);
		assertEquals(trainer.getPlayerLocation(), new Point(3,3));
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
		
		//*****Movement testing**************
		
		trainer.setDirectionFacing(Direction.EAST);
		Point p = trainer.getPlayerLocation();
		double x = p.getX();
		double y = p.getY();
		assertEquals(trainer.getDirectionFacing(), Direction.EAST);
		trainer.moveTrainer(Direction.SOUTH);
		assertEquals(trainer.getSteps(), 1);
		assertEquals(trainer.getDirectionFacing(), Direction.SOUTH);
		assertTrue(trainer.getPlayerLocation().getY()== y+1);
		trainer.moveTrainer(Direction.EAST);
		assertEquals(trainer.getSteps(), 2);
		assertEquals(trainer.getDirectionFacing(), Direction.EAST);
		assertTrue(trainer.getPlayerLocation().getX()== x+1);
		
		//**********pokeball testing*********
		trainer.throwPokeball();
		assertEquals(trainer.getPokeballsRemaining(), 14);
		
		//****COSTUME TEST
		BufferedImage img;
		try {
			img = ImageIO.read(new File("./images/Sponge.png"));
			trainer.setCostume(img);
			assertEquals(trainer.getCostume(), img);
		} catch (IOException e) {
			System.out.println("IMAGE NOT FOUND");
			e.printStackTrace();
		}
		
		//*****steps test
		
		while(trainer.getSteps()<Trainer.Max_Steps){
			trainer.incrementSteps(1);		
		}
		assertTrue(trainer.gameOver()==true);
		
		
		
		
		
	}

}
