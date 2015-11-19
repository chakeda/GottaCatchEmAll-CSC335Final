package model;

public class FishingPole extends Item{

	public FishingPole() {
		super("FishingPole", Category.HOLD_ITEM);
		this.foundByTrainer = false; 
	}
	public FishingPole(String name, Category catagory){
		super(name, catagory); 
	}
	@Override
	public void update() {
		// fish pokemon out of the water change something to true probably
		this.foundByTrainer= true; 
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
