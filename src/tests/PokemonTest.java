package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Scyther;

public class PokemonTest {

	@Test
	public void testConstructor() {
		Scyther s = new Scyther();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(75, s.getEscapability());
		assertEquals(25, s.getCatchability());
	}
	
	@Test
	public void throwRockAtPokemon(){
		Scyther s = new Scyther();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(85, s.getEscapability());
		assertEquals(30, s.getCatchability());
	}
	
	@Test
	public void throwBaitAtPokeomon(){
		Scyther s = new Scyther();
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(65, s.getEscapability());
		assertEquals(20, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocks(){
		Scyther s = new Scyther();
		s.throwRock();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(95, s.getEscapability());
		assertEquals(35, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocks(){
		Scyther s = new Scyther();
		s.throwRock();
		s.throwRock();
		s.throwRock();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(100, s.getEscapability());
		assertEquals(40, s.getCatchability());
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
		assertEquals(45, s.getEscapability());
		assertEquals(10, s.getCatchability());
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
		assertEquals(25, s.getEscapability());
		assertEquals(0, s.getCatchability());
		s.throwBait();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(15, s.getEscapability());
		assertEquals(0, s.getCatchability());
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

}
