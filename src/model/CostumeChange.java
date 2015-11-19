package model;

public class CostumeChange extends Item{

	public CostumeChange() {
		super("CostumeChange", Category.HOLD_ITEM); 
		this.foundByTrainer = false; 
	}
	public CostumeChange(String name, Category category){
		super(name, category); 
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
