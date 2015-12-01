package model.items;

import java.io.Serializable;

import model.Category;
import model.Item;

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


}
