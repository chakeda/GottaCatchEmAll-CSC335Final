package model.pokemon;

import java.io.Serializable;

import model.Pokemon;

public class Chansey extends Pokemon implements Serializable{
	
	private static final int RARITY = 5;
	private static final int DURATION = 10;
	private static final int ESCAPABILITY = 25;
	private static final int CATCHABILITY = 50;
	private static final int ESCAPABILITYCONSTANT = 5;
	private static final int CATCHABILITYCONSTANT = 5;
	private static final int HP = 250;
	
	
	public Chansey(){ 
		this.setCatchability(CATCHABILITY);
		this.setDuration(DURATION);
		this.setEscapability(ESCAPABILITY);
		this.setRarity(RARITY); //took out battle image
		this.setHP(HP);
		this.setName("Chansey");
	}
	
	
	@Override
	public void increaseEscapability() {
		int newEscapability = this.getEscapability() + ESCAPABILITYCONSTANT;
		if(newEscapability > 100)
			newEscapability = 100;
		this.setEscapability(newEscapability);
	}
	@Override
	public void increaseCatchability() {
		int newCatchAbility = this.getCatchability() + CATCHABILITYCONSTANT;
		if(newCatchAbility > 100)
			newCatchAbility = 100;
		this.setCatchability(newCatchAbility);		
	}
	
	@Override
	public void decreaseEscapability() {
		int newEscapability = this.getEscapability() - ESCAPABILITYCONSTANT;
		if(newEscapability < 0)
			newEscapability = 0;
		this.setEscapability(newEscapability);
	}
	@Override
	public void decreaseCatchability() {
		int newCatchAbility = this.getCatchability() - CATCHABILITYCONSTANT;
		if(newCatchAbility < 0)
			newCatchAbility = 0;
		this.setCatchability(newCatchAbility);	
	}


}
