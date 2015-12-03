package model;

public class CostumeChange extends Item{

	public CostumeChange(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		this.foundByTrainer = true; 
		
	}

}
