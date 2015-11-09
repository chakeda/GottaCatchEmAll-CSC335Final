package model;

import java.awt.Image;

public abstract class Pokemon {
	
	private Image battleImage; //can later change to a collection of images/sprite
	private Image caughtImage; //smaller image similar to seeing the 5 pokemon you currently have
	private int rarity; // rarity, 0-10
	private int duration; //max duration of battle before escaping
	private int escapability; //likelihood to escape (higher for more rare pokemon) 
	private int catchability; //likelihood to be caught
	
	public void setRarity(int r){
		rarity = r;
	}
	public int getRarity(){
		return rarity;
	}
	
	
	public void setDuration(int d){
		duration = d;
	}
	public int getDuration(){
		return duration;
	}
	
	
	public void setEscapability(int e){
		escapability = e;
	}
	public int getEscapability(){
		return escapability;
	}
	
	
	public void setCatchability(int c){
		catchability = c;
	}
	public int getCatchability(){
		return catchability;
	}
	
	//the following getImages can be used later for gui purposes
	
	public void setBattleImage(Image i){
		battleImage = i;
	}
	public Image getBattleImage(){
		return battleImage;
	}
	
	
	public void setCaughtImage(Image c){
		caughtImage = c;
	}
	public Image getCaughtImage(){
		return caughtImage;
	}
	
	
	// The following four functions are unique to each pokemon
	public abstract void increaseEscapability();
	public abstract void decreaseEscapability();
	
	public abstract void increaseCatchability();
	public abstract void decreaseCatchability();
	
	public void throwRock(){
		increaseEscapability();
		increaseCatchability();
	}
	
	public void throwBait(){
		decreaseEscapability();
		decreaseCatchability();
	}
}
