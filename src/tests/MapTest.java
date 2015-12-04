package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Direction;
import model.Map;

public class MapTest {
	
	// gui intensive!!! try visual testing. 

	@Test
	public void testTheBasics() {
		// instantiate!
		Map map = new Map();
		
		// test setters
		map.setBush(16, 16);
		assertEquals(map.getTileAt(16, 16), "B");
		map.setGrass(17, 17);
		assertEquals(map.getTileAt(17, 17), "G");
		
		// tiles already there
		assertEquals(map.getTileAt(5, 5), "G");
		
		// so is the trainer. test his location
		assertEquals(map.getFogAt(0, 7), "T");
		assertEquals(map.getTrainerX(), 7);
		assertEquals(map.getTrainerY(), 0);
		
		// test one movement
		map.moveTrainer(Direction.SOUTH); 
		assertEquals(map.getFogAt(1, 7), "T");
		assertEquals(map.getTrainerX(), 7); 
		assertEquals(map.getTrainerY(), 1);
		
		// just test this too
		assertEquals(map.getMapLength(), 32);
		
		// test inability to walk out of bounds
		map.moveTrainer(Direction.NORTH); 
		assertEquals(map.getFogAt(0, 7), "T");
		assertNull(map.moveable(Direction.NORTH));
		// note: running a moveTrainer("up") will cause an out of bounds exception.
		
		// test inability to walk on bushes
		assertEquals(map.getTileAt(1, 1), "B");
		map.moveTrainer(Direction.SOUTH); 
		map.moveTrainer(Direction.WEST); 
		assertNull(map.moveable(Direction.WEST));
		assertNotNull(map.moveable(Direction.SOUTH));
		
		// for code coverage
		map.moveTrainer(Direction.WEST); 
		map.moveTrainer(Direction.WEST); 
		map.moveTrainer(Direction.SOUTH); 
		assertEquals(map.getFogAt(2, 4), "T");
		assertNull(map.moveable(Direction.NORTH));


		// try to get more code coverage
		for(int i=4; i<map.getMapLength()-1; i++){
			map.moveTrainer(Direction.EAST); 	
		}
		assertNull(map.moveable(Direction.EAST));
		
		// initialize pokemon
		map.initializePokemonList();
		
		// make map 2
		Map map2 = new Map("map2");
		assertFalse(map2.beginPokemonBattle(20,20)); // false-- not on grass
		
		// We can go from top left to bottom left... so the problem is in the view
		for(int i=4; i<map.getMapLength()-1; i++){
			map.moveTrainer(Direction.WEST); 	
		}
		assertEquals(map.getFogAt(2, 4), "T");
		for(int i=0; i<20; i++){
			map.moveTrainer(Direction.SOUTH); 	
		}
		assertEquals(map.getFogAt(22, 4), "T");

		
		
	
	}	

}
