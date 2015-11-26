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

}
