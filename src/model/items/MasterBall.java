package model.items;

import model.Category;
import model.Item;

public class MasterBall extends Item{

	public MasterBall(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		// Make the Trainers catchability 100%
		this.foundByTrainer = true; 
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
