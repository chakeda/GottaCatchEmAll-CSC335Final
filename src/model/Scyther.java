package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Scyther extends Pokemon{
	
	private static int RARITY = 7; // 1-10
	private static int DURATION = 5; //5 minutes, after five minutes, pokemon will flee
	private static int ESCAPABILITY = 75; // out of a possible 100, with 100 being it will always escape
	private static int CATCHABILITY = 25; //out of a possible 100, with 100 being it will always be caught
	
	public Scyther(){
		this.setCatchability(CATCHABILITY);
		this.setDuration(DURATION);
		this.setEscapability(ESCAPABILITY);
		this.setRarity(RARITY);
		try{
			this.setBattleImage(ImageIO.read(new File("./images/pokemonImages/scyther.gif")));
		}catch (IOException e){
			e.printStackTrace();
		}
		try{
			this.setCaughtImage(ImageIO.read(new File("./images/pokemonImages/scyther.gif")));
		}catch (IOException e){
			e.printStackTrace();
		}	
	}
	
	//The +-10 or +-5 below are unique to each pokemon, can be changed later
	//
	@Override
	public void increaseEscapability() {
		int newEscapability = this.getEscapability() + 10;
		if(newEscapability > 100)
			newEscapability = 100;
		this.setEscapability(newEscapability);
	}
	@Override
	public void increaseCatchability() {
		int newCatchAbility = this.getCatchability() + 5;
		if(newCatchAbility > 100)
			newCatchAbility = 100;
		this.setCatchability(newCatchAbility);		
	}
	
	@Override
	public void decreaseEscapability() {
		int newEscapability = this.getEscapability() - 10;
		if(newEscapability < 0)
			newEscapability = 0;
		this.setEscapability(newEscapability);
	}
	@Override
	public void decreaseCatchability() {
		int newCatchAbility = this.getCatchability() - 5;
		if(newCatchAbility < 0)
			newCatchAbility = 0;
		this.setCatchability(newCatchAbility);	
	}


}
