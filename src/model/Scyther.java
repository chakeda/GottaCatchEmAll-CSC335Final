package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Scyther extends Pokemon{
	
	private static final int RARITY = 7; // 1-10
	private static final int DURATION = 5; //5 minutes, after five minutes, pokemon will flee
	private static final int ESCAPABILITY = 50; // out of a possible 100, with 100 being it will always escape
	private static final int CATCHABILITY = 75; //out of a possible 100, with 100 being it will always be caught
	private static final int ESCAPABILITYCONSTANT = 10;
	private static final int CATCHABILITYCONSTANT = 5;
	
	
	public Scyther(){
		this.setCatchability(CATCHABILITY);
		this.setDuration(DURATION);
		this.setEscapability(ESCAPABILITY);
		this.setRarity(RARITY);
		/** Yo this doesnt compile
		try{ //for now, caughtImage is same as battle image (64x64)
			this.setBattleImage(ImageIO.read(new File("./images/pokemonImages/scyther.gif")));
			this.setCaughtImage(ImageIO.read(new File("./images/pokemonImages/scyther.gif")));
		}catch (IOException e){
			e.printStackTrace();
		}	
		**/
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
