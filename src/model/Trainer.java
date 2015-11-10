package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Trainer {

	String PlayerName;
	private int numberOfSteps;
	private int pokemonCaught;
	public static final int Max_Steps = 500;
	public static final int Max_Pokemon_Caught = 5;
	public static final int StartNumOfPokeballs = 15;
	private ArrayList<Pokemon> caughtPokemon;
	private ArrayList<Item> itemList;
	private Item usingItem;
	private int pokeballsRemaining;
	private Direction dir;
	private BufferedImage costume;
	private Point playerLocation;
	
	public Trainer(String PlayerName){		//initialize instance variables
		this.PlayerName = PlayerName;
		numberOfSteps = 0;
		pokemonCaught= 0;
		caughtPokemon = new ArrayList<Pokemon>();
		itemList = new ArrayList<Item>();
		usingItem =null;
		pokeballsRemaining = StartNumOfPokeballs;
		dir = Direction.NORTH;
		try {
			costume = ImageIO.read(new File("./images/trainer.gif"));
		} catch (IOException e) {
			System.out.println("Image Not Found");
			e.printStackTrace();
		}
		playerLocation = new Point(3,3);
	}
	
	//*************GETTERS*************************
	public int getSteps(){
		return numberOfSteps;
	}
	public int getPokemonCaught(){
		return pokemonCaught;
	}
	public ArrayList<Pokemon> getPokemonList(){
		return caughtPokemon;
	}
	public ArrayList<Item> getItemList(){
		return itemList;
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
	public Point getPlayerLocation(){
		return playerLocation;
	}
	public BufferedImage getCostume(){
		return costume;
	}
	//******SETTERS*************************************
	public void setItemUsing(Item item){
		usingItem = item;
	}
	public void setDirectionFacing(Direction d){
		dir = d;
	}
	public void setPlayerLocation(int x, int y){
		 playerLocation = new Point(x,y);
	}
	public void setCostume(BufferedImage bi){
		costume = bi;
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
	
	//Implement this Method with map.movable???
	public void moveTrainer(Direction d){
		dir = d;
		//draw player facing the current direction
		if(numberOfSteps<Max_Steps){
			if(dir==Direction.NORTH){
				//is movable? 
				playerLocation.y-=1;
				incrementSteps(1);
			}
			if(dir==Direction.SOUTH){
				//is movable? 
				playerLocation.y+=1;
				incrementSteps(1);
			}
			if(dir==Direction.EAST){
				//is movable?
				playerLocation.x+=1;
				incrementSteps(1);
			}
			if(dir==Direction.WEST){
				//is movable?
				playerLocation.x-=1;
				incrementSteps(1);
			}	
		}
	}
	
	public boolean gameOver(){
		
		for(Pokemon pokemon:caughtPokemon){
			if(pokemon.equals("RICK MERCER MERMAID")){
				return true;
			}
		}
		if(numberOfSteps == Max_Steps){
			return true;
		}
		else if(pokemonCaught==Max_Pokemon_Caught){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
