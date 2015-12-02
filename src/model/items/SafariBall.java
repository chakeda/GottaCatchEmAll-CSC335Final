package model.items;

import model.Category;
import model.Item;

public class SafariBall extends Item{

	public SafariBall(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		// Make the Trainers catchability 100%
		this.foundByTrainer = true; 
		
	}

}
