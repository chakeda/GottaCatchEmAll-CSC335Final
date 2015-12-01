package tests;

import static org.junit.Assert.*;
import model.Category;
import model.Direction;
import model.Item;
import model.Trainer;
import model.items.CostumeChange;
import model.items.FishingPole;
import model.items.SafariBall;
import model.items.RunningShoes;

import org.junit.Test;

public class ItemTest {

	@Test
	public void test() {
		Trainer trainer = new Trainer("Ash");
		SafariBall mb = new SafariBall("master", Category.POKEBALLS);
		FishingPole fp = new FishingPole("fishPole", Category.HOLD_ITEM);
		RunningShoes rs = new RunningShoes("flash", Category.HOLD_ITEM);
		CostumeChange cc = new CostumeChange("change", Category.HOLD_ITEM);

		// Test all of the individual items
		assertEquals(mb.getName(), "master");
		assertEquals(mb.getCategory(), Category.POKEBALLS);
		assertFalse(mb.getCategory().equals(Category.BERRIES));
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
		assertTrue(trainer.getItemUsing().equals(mb));
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
		SafariBall mb = new SafariBall("master", Category.POKEBALLS);
		assertEquals(mb.getName(), "master");
		assertEquals(mb.getCategory(), Category.POKEBALLS);
		mb.setName("MASTER");
		mb.setCategory(Category.HOLD_ITEM);
		assertEquals(mb.getName(), "MASTER");
		assertEquals(mb.getCategory(), Category.HOLD_ITEM);
	}

	@Test
	public void testEnums() {
		System.out.println(Category.values());
		System.out.println(Direction.values());
	}

}
