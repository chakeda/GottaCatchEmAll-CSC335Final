package tests;

import static org.junit.Assert.*;
import model.Category;
import model.Direction;
import model.Item;
import model.Trainer;
import model.items.BerryJuice;
import model.items.CostumeChange;
import model.items.EnergyRoot;
import model.items.FishingPole;
import model.items.FreshWater;
import model.items.Lemonade;
import model.items.MasterBall;
import model.items.SafariBall;
import model.items.RunningShoes;

import org.junit.Test;

public class ItemTest {

	@Test
	public void test() {
		Trainer trainer = new Trainer("Ash");
		SafariBall sb = new SafariBall("master", Category.POKEBALLS);
		MasterBall mb = new MasterBall("ball", Category.POKEBALLS);
		FishingPole fp = new FishingPole("fishPole", Category.HOLD_ITEM);
		RunningShoes rs = new RunningShoes("flash", Category.HOLD_ITEM);
		CostumeChange cc = new CostumeChange("change", Category.HOLD_ITEM);
		Lemonade l = new Lemonade("Lemon", Category.BERRIES);
		EnergyRoot e= new EnergyRoot("Energy", Category.MEDICINE);
		FreshWater w = new FreshWater("Water", Category.MEDICINE);
		BerryJuice b = new BerryJuice("Berry", Category.BERRIES);
		// Test all of the individual items
		assertEquals(mb.getName(), "ball");
		assertEquals(mb.getCategory(), Category.POKEBALLS);
		assertEquals(l.getName(), "Lemon");
		assertEquals(l.getCategory(), Category.BERRIES);
		assertEquals(e.getName(), "Energy");
		assertEquals(e.getCategory(), Category.MEDICINE);
		assertEquals(w.getName(), "Water");
		assertEquals(w.getCategory(), Category.MEDICINE);
		assertEquals(b.getName(), "Berry");
		assertEquals(b.getCategory(), Category.BERRIES);
		assertFalse(sb.getCategory().equals(Category.BERRIES));
		assertEquals(fp.getName(), "fishPole");
		assertEquals(fp.getCategory(), Category.HOLD_ITEM);
		assertEquals(rs.getName(), "flash");
		assertEquals(rs.getCategory(), Category.HOLD_ITEM);
		assertEquals(cc.getName(), "change");
		assertEquals(cc.getCategory(), Category.HOLD_ITEM);

		// ArrayList<Item> itemList = new ArrayList<Item>();

		// set the trainers item and test to make sure it is true
		trainer.setItemUsing(mb);
		trainer.addToItemList(mb);
		mb.update();		
		trainer.setItemUsing(l);
		trainer.addToItemList(l);
		l.update();
		assertTrue(trainer.getItemUsing().equals(l));
		trainer.setItemUsing(e);
		trainer.addToItemList(e);
		e.update();
		assertTrue(trainer.getItemUsing().equals(e));
		trainer.setItemUsing(w);
		trainer.addToItemList(w);
		w.update();
		assertTrue(trainer.getItemUsing().equals(w));
		trainer.setItemUsing(b);
		trainer.addToItemList(b);
		b.update();
		assertTrue(trainer.getItemUsing().equals(b));
		trainer.setItemUsing(sb);
		trainer.addToItemList(sb);
		sb.update();
		assertTrue(trainer.getItemUsing().equals(sb));
		trainer.setItemUsing(fp);
		trainer.addToItemList(fp);
		fp.update();
		assertTrue(trainer.getItemUsing().equals(fp));
		trainer.setItemUsing(rs);
		trainer.addToItemList(rs);
		rs.update();
		assertTrue(trainer.getItemUsing().equals(rs));
		trainer.setItemUsing(cc);
		trainer.addToItemList(cc);
		cc.update();
		assertTrue(trainer.getItemUsing().equals(cc));

		// iterate through items list and make sure that all items have been
		// found
		/**
		 * Changed getItemList() to return List<String> for(Item
		 * items:trainer.getItemList()){ assertTrue(items.isFound()); }
		 **/
	}

	@Test
	public void testAbstractItemSetters() {
		SafariBall sb = new SafariBall("master", Category.POKEBALLS);
		assertEquals(sb.getName(), "master");
		assertEquals(sb.getCategory(), Category.POKEBALLS);
		sb.setName("MASTER");
		sb.setCategory(Category.HOLD_ITEM);
		assertEquals(sb.getName(), "MASTER");
		assertEquals(sb.getCategory(), Category.HOLD_ITEM);
	}

	@Test
	public void testEnums() {
		System.out.println(Category.values());
		System.out.println(Direction.values());
	}

}
