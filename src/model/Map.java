package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import model.items.CostumeChange;
import model.items.FishingPole;
import model.items.RunningShoes;
import model.pokemon.Chansey;
import model.pokemon.Cubone;
import model.pokemon.Kangaskhan;
import model.pokemon.Nidoran;
import model.pokemon.Paras;
import model.pokemon.Pinsir;
import model.pokemon.Rhyhorn;
import model.pokemon.Scyther;
import model.pokemon.Tauros;
import model.pokemon.Venomoth;

public class Map extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * Instantiation and its methods
	 */
	
	//// note: making map into Object or Character causes null pointers 
	private String[][] map;
	private String[][] mapFog;
	private Item[][] mapItems;

	
	// instantiate
	public Map(){
		map = new String[32][32]; // background
		mapFog = new String[32][32]; // foreground
		mapItems = new Item[32][32];
		
		cleanNulls();
		
		//// statically generate map 
		
		// map1.1: top left (0,16), (0,16)
		setTrainer(0,7); // trainer starts here
		setGrass(4,4);
		setGrass(5,5);
		setGrass(4,5);
		setGrass(5,4);
		setGrass(0,2);
		setGrass(0,3);
		setGrass(0,4);
		setGrass(0,5);
		setGrass(2,4);
		setGrass(2,3);
		setGrass(3,3);
		setGrass(3,4);
		
		setBush(7,7);
		setBush(1,1);
		setBush(1,2);
		setBush(1,3);
		setBush(1,4);
		setBush(1,5);
		setBush(1,6);
		setBush(0,6);
		setItem(new RunningShoes("Running Shoes", Category.HOLD_ITEM), 10,10); 
		
		// map1.2: top right (0,16), (17,32)
		setGrass(0,19);
		setGrass(1,19);
		setGrass(2,19);
		setGrass(0,20);
		setGrass(1,20);
		setGrass(2,20);
		setBush(10,20);
		setBush(11,21);
		setBush(12,22);
		setBush(13,23);
		setItem(new FishingPole("Fishing Pole", Category.HOLD_ITEM), 26,26); 
		
		// map1.3: bottom left (17,32), (0, 16)
		setGrass(29,2);
		setGrass(30,2);
		setGrass(31,2);
		setGrass(29,3);
		setGrass(30,3);
		setGrass(31,3);
		setGrass(29,4);
		setGrass(30,4);
		setGrass(31,4);
		setBush(18,12);
		setBush(25,5);
		setBush(31,9);
		
		// map1.4: bottom right (17,32),(17,32)
		setGrass(18,18);
		setGrass(20,18);
		setGrass(22,18);
		setGrass(24,18);
		setGrass(18,19);
		setGrass(20,19);
		setGrass(22,19);
		setGrass(24,19);
		setBush(18,20);
		setBush(20,20);
		setBush(22,20);
		setBush(22,20);
		setBush(18,21);
		setBush(20,21);
		setBush(22,21);
		setBush(22,21);
		setItem(new CostumeChange("Costume Change", Category.HOLD_ITEM), 25, 25);
		
		for(int i=0; i<7; i++){
			setBush(4,i);
		}
		for(int i=5; i<14; i++){
			setBush(i,6);
		}
		for(int i=5; i<22; i++){
			for(int j = 0; j<6; j++){
				setGrass(i,j);
			}
		}
		setGrass(0,0);
		setGrass(0,1);
		setGrass(0,0);
		setGrass(1,0);
		setGrass(2,0);
		setGrass(2,1);
		setGrass(2,2);
		setGrass(3,0);
		setGrass(3,1);
		setGrass(3,2);
		
		for(int i=0; i<3; i++){
			for(int j=12; j<26; j++){
				setGrass(i,j);
			}
		}
		
		for(int i=0; i<12; i++){
			for(int j=31; j>28; j--){
				setWater(i,j);
			}
		}
		
		setBush(31,17);
		setBush(30,17);
		setBush(29,17);
		setBush(28,17);
		setBush(31,31);
		setBush(30,31);
		setBush(29,31);
		setBush(28,31);
		for(int i = 19; i<31; i++){
			setBush(28, i);
		}
		for(int i=20; i<30; i++){
			setWater(30, i);
		}
		for(int i=19; i<31; i++){
			setGrass(29, i);
		}
		for(int i=19; i<31; i++){
			setGrass(31, i);
		}
		setGrass(30,30);
		setGrass(30,19);
		setGrass(30,18);
		setGrass(31,18);
		setGrass(29,18);
		
		for(int i=8; i<18; i++){
			for(int j=7; j<18; j++)
			setGrass(i, j);
		}
		for(int i=7; i<18; i++){
			setBush(18, i);
		}
		for(int i=7; i<18; i++){
			setWater(19, i);
		}
		for(int i=14; i<22;i++){
			setGrass(i,6);
		}
		for(int i=26; i<32; i++){
			setBush(i, 5);
		}
		for(int i=25; i<30; i++){
			for(int j=0; j<5; j++){
				setGrass(i,j);
			}
		}
		setWater(31,0);
		setWater(31,1);
		setWater(30,0);
		setWater(30,1);
		setWater(29,0);
		setWater(29,1);
	}
	
	public List<Pokemon> initializePokemonList(){
		List<Pokemon> allPokemon = new ArrayList<Pokemon>();
		allPokemon.add(new Chansey());
		allPokemon.add(new Cubone());
		allPokemon.add(new Kangaskhan());
		allPokemon.add(new Nidoran());
		allPokemon.add(new Paras());
		allPokemon.add(new Pinsir());
		allPokemon.add(new Rhyhorn());
		allPokemon.add(new Tauros());
		allPokemon.add(new Venomoth());
		return allPokemon;
	}
	
	// This is map #2. Built with any string as parameter
	public Map(String anyString){
		map = new String[32][32]; // background
		mapFog = new String[32][32]; // foreground
		mapItems = new Item[32][32];
		
		cleanNulls();
		
		//// statically generate map 
		
		// map2.1: top left (0,16), (0,16) - just make loads of grass
		setTrainer(8,8); // trainer starts here
		for (int i=0; i<16; i++){
			for (int j=0; j<16; j++){
				if (i==8 && j==8){
					// do nothing
				}else{
					setGrass(i, j);					
				}
			}
		}
		
		// map2.2: top right (0,16), (17,32)
		
		// map2.3: bottom left (17,32), (0, 16)

		// map2.4: bottom right (17,32),(17,32)
	}
	
	// sets grass
	public void setGrass(int k, int j){
		map[k][j] = "G";
	}
	
	// sets bush (unwalkable terrain)
	public void setBush(int k, int j){
		map[k][j] = "B";
	}
	
	// sets an Item (unwalkable terrain)
	// another parallel map exists to store items
	public void setItem(Item item, int k, int j){
		map[k][j] = "I"; // add to map
		mapItems[k][j] = item;
	}
	
	// sets a hunter
	public void setTrainer(int k, int j){
		mapFog[k][j] = "T";
	}
	
	public void setWater(int k, int j){
		map[k][j] = "W";
	}

	/***
	 * helper methods
	 */
	
	// clean nulls, replace with empty Strings. prevents NPE
	private void cleanNulls(){
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (map[i][j] == null){
					map[i][j] = " ";
				}
			}
		}
	}

	
	/***
	 * Movement methods
	 */
	
	// if the space is a bush/tree whatever, or is out of bounds, do not move in that direction
	public boolean moveable(String direction){
		int tempI = 0;
		int tempJ = 0;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){				
					if (direction.equals("up")){
						tempI = i - 1;
						tempJ = j;
					}
					if (direction.equals("right")){
						tempI = i;
						tempJ = j + 1;
					}
					if (direction.equals("down")){
						tempI = i + 1;
						tempJ = j;	
					}
					if (direction == "left"){
						tempI = i;
						tempJ = j - 1;
					}
					if(!checkMoveable(tempI, tempJ)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	// can I move there
	private boolean checkMoveable(int tempI, int tempJ){
		if (movementIsWithinBounds(tempI,tempJ)){ 
			if (map[tempI][tempJ].equals("B")){ 
				return false;
			}
			if (map[tempI][tempJ].equals("I")){
				// Added to inventory in MapView.
				map[tempI][tempJ] = ""; // turn ball to plain
				return true;
			}
		}else{
			return false;
		}
		return true;
	} 
	
	// computes probability, returns true if an encounter occurs.
	public boolean beginPokemonBattle(int tempI, int tempJ){
		// only called in grass
		if (map[tempI][tempJ].equals("G")){ 
			// pokemon spawn chance is 30%.
			Random generator = new Random(); 
			int random = generator.nextInt(10) + 1; // 1-10
			if (random % 3 == 0){
				return true;
			}
		}
		return false;
	}
	
	// return which pokemon
	public Pokemon whoToBattle(){
		Random generator = new Random(); 
		ArrayList<Pokemon> allPokemon = (ArrayList<Pokemon>)initializePokemonList();
		Collections.shuffle(allPokemon);
		
		int random = generator.nextInt(10) + 1; // 1-10	
		for(Pokemon p: allPokemon){
			if(p.getRarity() <= random){
				return p;
			}
		}
		return new Nidoran();
	}
	
	// sees if the movement is within bounds, used in moveable()
	public boolean movementIsWithinBounds(int tempI, int tempJ){
		// prevents out of bounds. In future we'll have a bunch of trees
		if (tempI == -1){
			return false;
		}
		if (tempI == map.length){
			return false;
		}
		if (tempJ == -1){
			return false;
		}
		if (tempJ == map.length){
			return false;
		}
		return true;
	}
	
	// move trainer, call movement observer. Note that out of bounds and obstacles are 
	//     accounted for in the mapview, so we do no error checking here
	public void moveTrainer(Direction dir){
		int tempI = 0;
		int tempJ = 0;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j] !=null && mapFog[i][j].equals("T")){				
					// previous space becomes map. get resultant position
					if (dir == Direction.NORTH){
						mapFog[i][j] = map[i][j];
						tempI = i - 1;
						tempJ = j;
					}
					if (dir == Direction.EAST){
						mapFog[i][j] = map[i][j];
						tempI = i;
						tempJ = j + 1;	
					}
					if (dir == Direction.SOUTH){
						mapFog[i][j] = map[i][j];
						tempI = i + 1;
						tempJ = j;
					}
					if (dir == Direction.WEST){
						mapFog[i][j] = map[i][j];
						tempI = i;
						tempJ = j - 1;
					} 
				}
			}
		}
		
		// the mapFog (foreground) will have the trainer
		mapFog[tempI][tempJ] = "T";
		
		// update gui
	    setChanged();
	    notifyObservers(dir);
	
	}
	
	/****
	 * Getters
	 */


	// get X location
	public int getTrainerX(){
		int xlocation = 0;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){
					xlocation = j;
				}
			}
		}
		
		return xlocation;
	}
	
	// get Y location
	public int getTrainerY(){
		int ylocation = 0;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){
					ylocation = i;
				}
			}
		}
		
		return ylocation;
	}
	
	// get map length
	public int getMapLength(){
		return map.length;
	}
	
	// get map element at coord
	public String getTileAt(int i, int j){
		return map[i][j];
	}
	
	// get map fog (trainer) at coord
	public String getFogAt(int i, int j){
		return mapFog[i][j];
	}
	
	// get itemMap unit
	public Item getItemAt(int i, int j){
		return mapItems[j][i];
	}
	
	public void removeItemAt(int i, int j){
		mapItems[i][j] = null;
	}
}
