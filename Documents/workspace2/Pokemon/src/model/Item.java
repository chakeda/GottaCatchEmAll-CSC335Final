package model;

import java.io.Serializable;

public abstract class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; 
	private Category category; 
	
	public Item(String name, Category category){
		this.name = name; 
		this.category = category;
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

}
