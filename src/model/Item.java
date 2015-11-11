package model;

import java.awt.Graphics;

public abstract class Item {
	private String name; 
	private Category category; 
	public boolean foundByTrainer; 
	
	public Item(String name, Category category){
		this.name = name; 
		this.category = category;
		foundByTrainer = false; 
	}
	public String getName(){
		return name; 
	}
	public void setName(String name){
		this.name = name; 
	}
	public Category getCategory(){
		return category; 
	}
	public void setCategory(Category category){
		this.category = category; 
	}
	public boolean isFound(){
		return foundByTrainer;
		
	}
	
	abstract public void update();//will be used when the object is found

}
