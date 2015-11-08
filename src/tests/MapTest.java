package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Map;

public class MapTest {
	
	// not much is testable. Map is gui intensive.
	// try visual testing. 

	@Test
	public void test() {
		Map map = new Map();
		
		map.setBush(16, 16);
		assertEquals(map.getTileAt(16, 16), "B");
		
		map.setGrass(17, 17);
		assertEquals(map.getTileAt(17, 17), "G");
		
		map.setTrainer(3, 3);
		assertEquals(map.getFogAt(3, 3), "T");
		assertEquals(map.getTrainerX(), 3);
		assertEquals(map.getTrainerY(), 3);
		
		map.moveTrainer("up"); 
		assertEquals(map.getFogAt(2, 3), "T");
		assertEquals(map.getTrainerX(), 2); 
		// yeah that's weird, but it makes sense in the gui's perspective
		assertEquals(map.getTrainerY(), 3);
		
		assertEquals(map.getMapLength(), 32);
	}

}
