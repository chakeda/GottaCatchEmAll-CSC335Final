package model;

public class MasterBall extends Item{

	public MasterBall(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		// Make the Trainers catchability 100%
		this.foundByTrainer = true; 
		
	}

}
