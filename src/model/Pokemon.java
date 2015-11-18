package model;

import java.util.Random;

public abstract class Pokemon {
	
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
	
		
	// The following four functions are unique to each pokemon
	public abstract void increaseEscapability();
	public abstract void decreaseEscapability();
	
	public abstract void increaseCatchability();
	public abstract void decreaseCatchability();
	
	public void rockThrown(){
		increaseEscapability();
		increaseCatchability();
	}
	
	public void baitThrown(){
		decreaseEscapability();
		decreaseCatchability();
	}
	
	
	public boolean isCaught(Random r){
		int ballVariable = r.nextInt(100);
		System.out.println(ballVariable);
		if(ballVariable > this.getCatchability())
			return true;
		else
			return false;
	}
	
	public boolean willRunAway(Random r){
		int runVariable = r.nextInt(100);
		System.out.println(runVariable);
		if(runVariable < this.getEscapability())
			return true;
		else
			return false;
	}
}
