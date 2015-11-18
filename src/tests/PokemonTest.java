package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.Chansey;
import model.Cubone;
import model.Kangaskhan;
import model.Nidoran;
import model.Scyther;

public class PokemonTest {

	/*** Begin Scyther Tests ***/
	@Test
	public void testConstructor() {
		Scyther s = new Scyther();
		assertEquals(7, s.getRarity());
		assertEquals(5, s.getDuration());
		assertEquals(50, s.getEscapability());
		assertEquals(75, s.getCatchability());
	}
	
	@Test
	public void rockThrownAtPokemon(){
		Scyther s = new Scyther();
		s.rockThrown();
		assertEquals(60, s.getEscapability());
		assertEquals(80, s.getCatchability());
	}
	
	@Test
	public void baitThrownAtPokeomon(){
		Scyther s = new Scyther();
		s.baitThrown();
		assertEquals(40, s.getEscapability());
		assertEquals(70, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocks(){
		Scyther s = new Scyther();
		s.rockThrown();
		s.rockThrown();
		assertEquals(70, s.getEscapability());
		assertEquals(85, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocks(){
		Scyther s = new Scyther();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(80, s.getEscapability());
		assertEquals(90, s.getCatchability());
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrows(){
		Scyther s = new Scyther();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(20, s.getEscapability());
		assertEquals(60, s.getCatchability());
	}
	
	@Test
	public void testThresholdsBaitThrows(){
		Scyther s = new Scyther();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(50, s.getCatchability());
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(45, s.getCatchability());
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(35, s.getCatchability());
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}

	
	@Test
	public void testisCaught(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		assertFalse(s.isCaught(testR)); //60
		assertFalse(s.isCaught(testR)); //48
		assertFalse(s.isCaught(testR)); //29
		assertFalse(s.isCaught(testR)); //47
		assertFalse(s.isCaught(testR)); //15
		assertFalse(s.isCaught(testR)); //53
		assertTrue(s.isCaught(testR)); //91
	}
	
	@Test
	public void testisCaughtAfterThrowingRock(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		s.rockThrown(); //Scyther Catchability is 80
		assertFalse(s.isCaught(testR)); //60
		assertFalse(s.isCaught(testR)); //48
		assertFalse(s.isCaught(testR)); //29
		assertFalse(s.isCaught(testR)); //47
		assertFalse(s.isCaught(testR)); //15
		assertFalse(s.isCaught(testR)); //53
		assertTrue(s.isCaught(testR)); //91
	}
	
	@Test
	public void testisCaughtAfterThrowingBait(){
		Random testR = new Random(0); //nexInt returns 60, less than Scyther Catchability of 75
		Scyther s = new Scyther();
		s.baitThrown(); //Scyther Catchability is 70
		assertFalse(s.isCaught(testR)); //60
		assertFalse(s.isCaught(testR)); //48
		assertFalse(s.isCaught(testR)); //29
		assertFalse(s.isCaught(testR)); //47
		assertFalse(s.isCaught(testR)); //15
		assertFalse(s.isCaught(testR)); //53
		assertTrue(s.isCaught(testR)); //91
		assertFalse(s.isCaught(testR)); //61
		assertFalse(s.isCaught(testR)); //19
		assertFalse(s.isCaught(testR)); //54
		assertTrue(s.isCaught(testR)); //77
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
		s.rockThrown(); //Scyther Escapability is now 60
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
		s.baitThrown(); //Scyther Escapability is now 40
		assertFalse(s.willRunAway(testR)); //60
		assertFalse(s.willRunAway(testR)); //48
		assertTrue(s.willRunAway(testR)); //29
		assertFalse(s.willRunAway(testR)); //47
		assertTrue(s.willRunAway(testR)); //15
		assertFalse(s.willRunAway(testR)); //53
		assertFalse(s.willRunAway(testR)); //91
	}
	
	
	/*** Begin Chansey Tests 
	private static final int RARITY = 5;
	private static final int DURATION = 10;
	private static final int ESCAPABILITY = 25;
	private static final int CATCHABILITY = 50;
	private static final int ESCAPABILITYCONSTANT = 5;
	private static final int CATCHABILITYCONSTANT = 5;
	 * 
	 * 
	 * 
	 * ***/
	@Test
	public void testChanseyConstructor() {
		Chansey c = new Chansey();
		assertEquals(5, c.getRarity());
		assertEquals(10, c.getDuration());
		assertEquals(25, c.getEscapability());
		assertEquals(50, c.getCatchability());
	}
	
	@Test
	public void rockThrownAtChansey(){
		Chansey c = new Chansey();
		c.rockThrown();
		assertEquals(30, c.getEscapability());
		assertEquals(55, c.getCatchability());
	}
	
	@Test
	public void baitThrownAtChansey(){
		Chansey s = new Chansey();
		s.baitThrown();
		assertEquals(20, s.getEscapability());
		assertEquals(45, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocksAtChansey(){
		Chansey s = new Chansey();
		s.rockThrown();
		s.rockThrown();
		assertEquals(35, s.getEscapability());
		assertEquals(60, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocksatChansey(){
		Chansey s = new Chansey();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(40, s.getEscapability());
		assertEquals(65, s.getCatchability());
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrowsAtChansey(){
		Chansey s = new Chansey();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(10, s.getEscapability());
		assertEquals(35, s.getCatchability());
	}
	
	@Test
	public void testThresholdsBaitThrowsAtChansey(){
		Chansey s = new Chansey();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(25, s.getCatchability());
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(20, s.getCatchability());
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(10, s.getCatchability());
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
		s.baitThrown();
		assertEquals(0, s.getCatchability());
	}
	
	/*** Begin Cubone Tests 
	private static final int RARITY = 3; //out of 10
	private static final int DURATION = 15;
	private static final int ESCAPABILITY = 10;
	private static final int CATCHABILITY = 15;
	private static final int ESCAPABILITYCONSTANT = 5;
	private static final int CATCHABILITYCONSTANT = 10;
	 * 
	 * 
	 * 
	 * ***/
	@Test
	public void testCuboneConstructor() {
		Cubone c = new Cubone();
		assertEquals(3, c.getRarity());
		assertEquals(15, c.getDuration());
		assertEquals(10, c.getEscapability());
		assertEquals(15, c.getCatchability());
	}
	
	@Test
	public void rockThrownAtCubone(){
		Cubone c = new Cubone();
		c.rockThrown();
		assertEquals(15, c.getEscapability());
		assertEquals(25, c.getCatchability());
	}
	
	@Test
	public void baitThrownAtCubone(){
		Cubone s = new Cubone();
		s.baitThrown();
		assertEquals(5, s.getEscapability());
		assertEquals(5, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocksAtCubone(){
		Cubone s = new Cubone();
		s.rockThrown();
		s.rockThrown();
		assertEquals(20, s.getEscapability());
		assertEquals(35, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocksatCubone(){
		Cubone s = new Cubone();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(25, s.getEscapability());
		assertEquals(45, s.getCatchability());
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrowsAtCubone(){
		Cubone s = new Cubone();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}
	
	@Test
	public void testThresholdsBaitThrowsAtCubone(){
		Cubone s = new Cubone();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}
	
	/*** Begin Kangaskhan Tests 
	private static final int RARITY = 8;
	private static final int DURATION = 5; 
	private static final int ESCAPABILITY = 85; 
	private static final int CATCHABILITY = 85; 
	private static final int ESCAPABILITYCONSTANT = 15;
	private static final int CATCHABILITYCONSTANT = 5;
	 * 
	 * 
	 * 
	 * ***/
	@Test
	public void testKangaskhanConstructor() {
		Kangaskhan c = new Kangaskhan();
		assertEquals(8, c.getRarity());
		assertEquals(5, c.getDuration());
		assertEquals(85, c.getEscapability());
		assertEquals(85, c.getCatchability());
	}
	
	@Test
	public void rockThrownAtKangaskhan(){
		Kangaskhan c = new Kangaskhan();
		c.rockThrown();
		assertEquals(100, c.getEscapability());
		assertEquals(90, c.getCatchability());
	}
	
	@Test
	public void baitThrownAtKangaskhan(){
		Kangaskhan s = new Kangaskhan();
		s.baitThrown();
		assertEquals(70, s.getEscapability());
		assertEquals(80, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocksAtKangaskhan(){
		Kangaskhan s = new Kangaskhan();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(95, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocksatKangaskhan(){
		Kangaskhan s = new Kangaskhan();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrowsAtKangaskhan(){
		Kangaskhan s = new Kangaskhan();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(40, s.getEscapability());
		assertEquals(70, s.getCatchability());
	}
	
	@Test
	public void testThresholdsBaitThrowsAtKangaskhan(){
		Kangaskhan s = new Kangaskhan();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(10, s.getEscapability());
		assertEquals(60, s.getCatchability());
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}
	
	/*** Begin Nidoran Tests 
	private static final int RARITY = 1;
	private static final int DURATION = 15;
	private static final int ESCAPABILITY = 5;
	private static final int CATCHABILITY = 5;
	private static final int ESCAPABILITYCONSTANT = 5;
	private static final int CATCHABILITYCONSTANT = 5;
	 * 
	 * 
	 * 
	 * ***/
	@Test
	public void testNidoranConstructor() {
		Nidoran c = new Nidoran();
		assertEquals(1, c.getRarity());
		assertEquals(15, c.getDuration());
		assertEquals(5, c.getEscapability());
		assertEquals(5, c.getCatchability());
	}
	
	@Test
	public void rockThrownAtNidoran(){
		Nidoran c = new Nidoran();
		c.rockThrown();
		assertEquals(10, c.getEscapability());
		assertEquals(10, c.getCatchability());
	}
	
	@Test
	public void baitThrownAtNidoran(){
		Nidoran s = new Nidoran();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}
	
	@Test
	public void testMultipleThrowsRocksAtNidoran(){
		Nidoran s = new Nidoran();
		s.rockThrown();
		s.rockThrown();
		assertEquals(15, s.getEscapability());
		assertEquals(15, s.getCatchability());
	}
	
	@Test
	public void testThresholdsThrowsRocksatNidoran(){
		Nidoran s = new Nidoran();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
		s.rockThrown();
		assertEquals(100, s.getEscapability());
		assertEquals(100, s.getCatchability());
	}
	
	@Test
	public void testMultipleBaitThrowsAtNidoran(){
		Nidoran s = new Nidoran();
		s.baitThrown();
		s.baitThrown();
		s.baitThrown();
		assertEquals(0, s.getEscapability());
		assertEquals(0, s.getCatchability());
	}
}