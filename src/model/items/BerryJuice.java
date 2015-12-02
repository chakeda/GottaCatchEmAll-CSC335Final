package model.items;

import model.Category;
import model.Item;

public class BerryJuice extends Item{
	//Increase by 20
	public BerryJuice(String name, Category category) {
		super(name, category);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.foundByTrainer = true;
		
	}

}
