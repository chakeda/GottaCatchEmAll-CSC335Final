package model;

import java.awt.Graphics;

public abstract class Item {
	private String name; 
	private Category category; 
	public Item(String name, Category category){
		this.name = name; 
		this.category = category;
	}
	public String getName(){
		return name; 
	}
	public Category getCategory(){
		return category; 
	}
	
	abstract public void update();

}
