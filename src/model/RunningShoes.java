package model;

import java.io.Serializable;

public class RunningShoes extends Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    