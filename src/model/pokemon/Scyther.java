package model.pokemon;

import model.Pokemon;

public class Scyther extends Pokemon{
	
	private static final int RARITY = 7; // 1-10
	private static final int DURATION = 5; //5 minutes, after five minutes, pokemon will flee
	private static final int ESCAPABILITY = 50; // out of a possible 100, the higher the number = more likely to run
	private static final int CATCHABILITY = 75; //out of a possible 100, the higher the number = harder to catch
	private static final int ESCAPABILITYCONSTANT = 10;
	private static final int CATCHABILITYCONSTANT = 5;
	
	
	public Scyther(){ 
		this.setCatchability(CATCHABILITY);
		this.setDuration(DURATION);
		this.setEscapability(ESCAPABILITY);
		this.setRarity(RARITY); //took out battle image
		this.setName("Scyther");
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
