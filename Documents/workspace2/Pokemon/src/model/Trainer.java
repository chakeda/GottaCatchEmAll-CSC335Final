package model;


import java.io.Serializable;
import java.util.ArrayList;



public class Trainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String playerName;
	private int numberOfSteps;
	private int pokemonCaught;
	private static final int MAX_POKEMON_CAUGHT = 5;
	private static final int START_NUM_POKEBALLS = 15;
	private ArrayList<Pokemon> caughtPokemon;
	private ArrayList<Item> itemList;
	private Item usingItem;
	private int pokeballsRemaining;
	private Direction dir;
	// private BufferedImage costume; // breaks serialization.

	
	public Trainer(String PlayerName){		//initialize instance variables
		this.playerName = PlayerName;
		numberOfSteps = 0;
		pokemonCaught= 0;
		caughtPokemon = new ArrayList<Pokemon>();
		itemList = new ArrayList<Item>();
		usingItem =null;
		pokeballsRemaining = START_NUM_POKEBALLS;
		dir = Direction.NORTH;
	}
	
	//*************GETTERS*************************
	public int getSteps(){
		return numberOfSteps;
	}
	public int getPokemonCaught(){
		return pokemonCaught;
	}
	public ArrayList<String> getPokemonList(){
		ArrayList<String> pokemon = new ArrayList<String>();
		for (Pokemon p: caughtPokemon){
			pokemon.add(p.getName());
		}
		return pokemon; 
	}
	public ArrayList<String> getItemList(){
		ArrayList<String> items = new ArrayList<String>();
		for (Item i: itemList){
			items.add(i.getName());
		}
		return items; 
	}
	public Item getItemUsing(){
		return usingItem;
	}
	public int getPokeballsRemaining(){
		return pokeballsRemaining;
	}
	public Direction getDirectionFacing(){
		return dir;
	}

	//******SETTERS*************************************
	public void setItemUsing(Item item){
		usingItem = item;
	}
	
	//******GAMEPLAY METHODS***********************************
	public void incrementSteps(int n){	//
		numberOfSteps = numberOfSteps+n;
	}
	public void incrementNumPokemon(){
		pokemonCaught+=1;;
	}
	public void addToPokemonList(Pokemon p){
		caughtPokemon.add(p);
		incrementNumPokemon();
	}
	public void addToItemList(Item item){
		itemList.add(item);
	}
	public void throwPokeball(){
		pokeballsRemaining-=1;
	}
	
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean gameOver(){
		
		for(Pokemon pokemon:caughtPokemon){
			if(pokemon.equals("RICK MERCER MERMAID")){
				return true;
			}
		}
		if(pokemonCaught==MAX_POKEMON_CAUGHT){
			return true;
		}
		if(numberOfSteps > 500){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
