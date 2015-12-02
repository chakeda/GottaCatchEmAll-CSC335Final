package model.items;

import java.io.Serializable;

import model.Category;
import model.Item;

public class FishingPole extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FishingPole(String name, Category catagory){
		super(name, catagory); 
	}
	@Override
	public void update() {
		// fish pokemon out of the water change something to true probably
		this.foundByTrainer= true; 
	}

}
