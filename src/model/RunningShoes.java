package model;

public class RunningShoes extends Item{

	public RunningShoes(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		this.foundByTrainer = true; 
	}

}
    