package model.items;

import java.io.Serializable;

import model.Category;
import model.Item;

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


}
    