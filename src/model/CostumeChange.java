package model;

import java.io.Serializable;

public class CostumeChange extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
