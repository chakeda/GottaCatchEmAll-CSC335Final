package model;

public class RunningShoes extends Item{
	
	public RunningShoes(String name, Category catagory){
		super(name, catagory); 
	}

	@Override
	public void update() {
		this.foundByTrainer = true; 
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
    