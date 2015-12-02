package model.items;

import java.io.Serializable;

import model.Category;
import model.Item;

public class Lemonade extends Item  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//increase Hp by 80
	public Lemonade(String name, Category category) {
		super(name, category);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
