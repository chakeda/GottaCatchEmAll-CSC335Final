package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.Scyther;

public class PokemonTest {

	@Test
	public void testConstructor() {
		Scyther s = new Scyther();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(50, s.getEscapability());
		assertEquals(75, s.getCatchability());
	}
	
	@Test
	public void throwRockAtPokemon(){
		Scyther s = new Scyther();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(60, s.getEscapability());
		assertEquals(80, s.getCatchability());
	}
	
	@Test
	public void throwBaitAtPokeomon(){
		Scyther s = new Scyther();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(40, s.getEscapability());
		assertEquals(70, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocks(){
		Scyther s = new Scyther();
		s.throwRock();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(70, s.getEscapability());
		assertEquals(85, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocks(){
		Scyther s = new Scyther();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(80, s.getEscapability());
		assertEquals(90, s.getCatchability());
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrows(){
		Scyther s = new Scyther();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(20, s.getEscapability());
		assertEquals(60, s.getCatchability());
	}
	
	@Test
	public void testThresholdsBaitThrows(){
		Scyther s = new Scyther();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(0, s.getEscapability());
		assertEquals(50, s.getCatchability());
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(0, s.getEscapability());
		assertEquals(45, s.getCatchability());
		s.throwBait();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(0, s.getEscapability());
		assertEquals(35, s.getCatchability());
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}

	
	@Test
	public void testThrowPokeBall(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		assertFalse(s.throwPokeball(testR)); //60
		assertFalse(s.throwPokeball(testR)); //48
		assertFalse(s.throwPokeball(testR)); //29
		assertFalse(s.throwPokeball(testR)); //47
		assertFalse(s.throwPokeball(testR)); //15
		assertFalse(s.throwPokeball(testR)); //53
		assertTrue(s.throwPokeball(testR)); //91
	}
	
	@Test
	public void testThrowPokeBallAfterThrowingRock(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		s.throwRock(); //Scyther Catchability is 80
		assertFalse(s.throwPokeball(testR)); //60
		assertFalse(s.throwPokeball(testR)); //48
		assertFalse(s.throwPokeball(testR)); //29
		assertFalse(s.throwPokeball(testR)); //47
		assertFalse(s.throwPokeball(testR)); //15
		assertFalse(s.throwPokeball(testR)); //53
		assertTrue(s.throwPokeball(testR)); //91
	}
	
	@Test
	public void testThrowPokeBallAfterThrowingBait(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		s.throwBait(); //Scyther Catchability is 70
		assertFalse(s.throwPokeball(testR)); //60
		assertFalse(s.throwPokeball(testR)); //48
		assertFalse(s.throwPokeball(testR)); //29
		assertFalse(s.throwPokeball(testR)); //47
		assertFalse(s.throwPokeball(testR)); //15
		assertFalse(s.throwPokeball(testR)); //53
		assertTrue(s.throwPokeball(testR)); //91
		assertFalse(s.throwPokeball(testR)); //61
		assertFalse(s.throwPokeball(testR)); //19
		assertFalse(s.throwPokeball(testR)); //54
		assertTrue(s.throwPokeball(testR)); //77
	}
	
	@Test
	public void testWillRunAway(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Escapability of 50
		Scyther s = new Scyther();
		assertFalse(s.willRunAway(testR)); //60
		assertTrue(s.willRunAway(testR)); //48
		assertTrue(s.willRunAway(testR)); //29
		assertTrue(s.willRunAway(testR)); //47
		assertTrue(s.willRunAway(testR)); //15
		assertFalse(s.willRunAway(testR)); //53
		assertFalse(s.willRunAway(testR)); //91
	}
	
	@Test
	public void testWillRunAwayAfterThrowingRock(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Escapability of 50
		Scyther s = new Scyther();
		s.throwRock(); //Scyther Escapability is now 60
		assertFalse(s.willRunAway(testR)); //60
		assertTrue(s.willRunAway(testR)); //48
		assertTrue(s.willRunAway(testR)); //29
		assertTrue(s.willRunAway(testR)); //47
		assertTrue(s.willRunAway(testR)); //15
		assertTrue(s.willRunAway(testR)); //53
		assertFalse(s.willRunAway(testR)); //91
	}
	
	@Test
	public void testWillRunAwayAfterThrowingBait(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Escapability of 50
		Scyther s = new Scyther();
		s.throwBait(); //Scyther Escapability is now 40
		assertFalse(s.willRunAway(testR)); //60
		assertFalse(s.willRunAway(testR)); //48
		assertTrue(s.willRunAway(testR)); //29
		assertFalse(s.willRunAway(testR)); //47
		assertTrue(s.willRunAway(testR)); //15
		assertFalse(s.willRunAway(testR)); //53
		assertFalse(s.willRunAway(testR)); //91
	}
}
