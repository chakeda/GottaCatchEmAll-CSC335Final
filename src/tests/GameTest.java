package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.Direction;
import model.Map;
import model.Trainer;

public class GameTest {

	@Test
	public void playGameToCompletion() {
		Map map = new Map();
		Trainer trainer = new Trainer("testy");
		for(int i=0; i<100; i++){
			Random generator = new Random(); 
			int random = generator.nextInt(10) + 1; // 1-10
			System.out.println(random);
		}

		
		for (int i=0; i<12; i++){
			map.moveTrainer(Direction.NORTH);
			trainer.incrementSteps(1);
			map.moveTrainer(Direction.SOUTH);
			trainer.incrementSteps(1);
			map.moveTrainer(Direction.EAST);
			trainer.incrementSteps(1);
			map.moveTrainer(Direction.WEST);
			trainer.incrementSteps(1);
		}
		
		map.moveTrainer(Direction.SOUTH);
		trainer.incrementSteps(1);
		map.moveTrainer(Direction.SOUTH);
		trainer.incrementSteps(1);
		
		assertTrue(trainer.gameOver());
	}

}
