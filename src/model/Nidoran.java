package model;

public class Nidoran extends Pokemon{
	
	private static final int RARITY = 1;
	private static final int DURATION = 15;
	private static final int ESCAPABILITY = 5;
	private static final int CATCHABILITY = 5;
	private static final int ESCAPABILITYCONSTANT = 5;
	private static final int CATCHABILITYCONSTANT = 5;
	private static final String NAME = "Nidoran";
	
	public Nidoran(){ 
		this.setCatchability(CATCHABILITY);
		this.setDuration(DURATION);
		this.setEscapability(ESCAPABILITY);
		this.setRarity(RARITY); //took out battle image
		this.setName(NAME);
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
