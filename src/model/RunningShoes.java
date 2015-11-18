package model;

public class RunningShoes extends Item{

	public RunningShoes() {
		super("RunningShoes", Category.BATTLE_ITEM);
		this.foundByTrainer = false;
	}
	
	public RunningShoes(String name, Category catagory){
		super(name, catagory); 
	}

	@Override
	public void update() {
		this.foundByTrainer = true; 
	}

}
    