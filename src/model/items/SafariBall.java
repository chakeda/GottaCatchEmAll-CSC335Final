package model.items;

import java.io.Serializable;

import model.Category;
import model.Item;

public class SafariBall extends Item  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SafariBall(String name, Category category) {
		super(name, category);
	}

	@Override
	public void update() {
		// Make the Trainers catchability 100%
		this.foundByTrainer = true; 
		
	}

}
