package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import model.items.BerryJuice;
import model.items.CostumeChange;
import model.items.EnergyRoot;
import model.items.FishingPole;
import model.items.FreshWater;
import model.items.Lemonade;
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
		
		setBush(0,5);
		setBush(1,5);
		setBush(1,4);
		setBush(1,3);
		setBush(1,2);
		
		setItem(new BerryJuice("Berry Juice", Category.BERRIES), 0,4);

		setGrass(2,0);
		setGrass(3,0);
		setGrass(2,1);
		setGrass(3,1);
		setGrass(2,2);
		setGrass(3,2);
		setGrass(2,3);
		setGrass(3,3);
		setGrass(2,4);
		setGrass(3,4);
		
		setBush(4,4);
		setBush(5,4);
		setBush(6,4);
		setBush(7,4);
		setBush(8,4);
		setBush(9,4);
		setBush(10,4);
		setBush(4,5);
		setBush(4,6);
		setBush(4,7);
		setBush(4,8);
		setBush(4,9);
		setBush(4,10);
		setBush(5,10);
		setBush(6,10);
		setBush(7,10);
		setBush(8,10);
		setBush(9,10);
		setBush(10,10);
		
		for (int i=5; i<10; i++){
			for (int j=5; j<10; j++){
				setGrass(i, j);
			}
		}
		
		setItem(new RunningShoes("Running Shoes", Category.HOLD_ITEM), 5,7); 

		for (int i=5; i<11; i++){
			for (int j=11; j<16; j++){
				setGrass(i, j);
			}
		}
		
		setBush(0,12);
		setBush(1,12);
		setBush(2,12);
		setBush(3,12);
		
		setBush(4,12);
		setBush(4,13);
		setBush(4,14);
		setBush(4,15);
		setBush(4,16);
		
		setBush(13,6);
		setBush(13,7);
		setBush(14,6);
		setBush(14,7);
		
		for (int i=13; i<16; i++){
			for (int j=8; j<10; j++){
				setGrass(i, j);
			}
		}
	
		for (int i=13; i<19; i++){
			for (int j=0; j<5; j++){ // goes to other board
				setWater(i, j);
			}
		}

		// map1.2: top right (0,16), (17,32)

		setBush(4, 17);
		setBush(4, 18);
		setBush(4, 19);
		setBush(4, 20);
		
		setItem(new CostumeChange("Costume Change", Category.HOLD_ITEM), 1, 18);
		
		for (int i=4; i<12; i++){
			for (int j=21; j<25; j++){
				setWater(i, j);
			}
		}
		
		for (int j=21; j<29; j++){
			setBush(12, j);
		}
		
		setBush(12, 26);
		setBush(12, 27);
		setBush(12, 28);
		setBush(12, 29);

		for (int i=13; i<18; i++){
			for (int j=18; j<30; j++){
				setGrass(i, j);
			}
		}
		
		setBush(9, 26);
		setBush(8, 26);
		setBush(7, 26);
		setBush(7, 27);
		setBush(7, 28);
		setBush(8, 28);
		setBush(9, 28);
		setItem(new EnergyRoot("Energy",Category.MEDICINE), 8,27);
		
		setWater(6, 26);		
		setWater(5, 26);
		setWater(4, 26);
		setWater(6, 27);
		setWater(5, 27);
		setWater(4, 27);
		setWater(6, 28);
		setWater(5, 28);
		setWater(4, 28);
		
		setGrass(29, 4);
		setGrass(30, 4);
		setGrass(29, 3);
		setGrass(30, 3);
		
		for (int i=0; i<4; i++){
			for (int j=20; j<26; j++){
				setGrass(i, j);
			}
		}

		// map1.3: bottom left (17,32), (0, 16)
		for (int i=23; i<30; i++){
			for (int j=5; j<10; j++){
				setWater(i, j);
			}
		}
		
		for (int i=22; i<30; i++){
			setBush(i, 4);
		}
		for (int i=22; i<30; i++){
			setBush(i, 11);
		}
		for (int j=4; j<12; j++){
			setBush(30, j);
		}
		
		setItem(new Lemonade("Lemonade", Category.MEDICINE), 26,14);

		for (int i=19; i<23; i++){
			for (int j=5; j<12; j++){
				setGrass(i, j);
			}
		}
		
		for (int i=25; i<30; i++){
			for (int j=0; j<4; j++){
				setGrass(i, j);
			}
		}
		
		setItem(new FreshWater("Water", Category.MEDICINE), 24,3);


		// map1.4: bottom right (17,32),(17,32)
		
		setBush(20, 20);
		setBush(20, 21);
		setBush(20, 22);
		setBush(20, 23);
		setBush(20, 24);
		setBush(20, 25);
		setBush(20, 26);
		
		setBush(26, 20);
		setBush(26, 21);
		setBush(26, 22);
		setBush(26, 23);
		setBush(26, 24);
		setBush(26, 25);
		setBush(26, 26);

		setGrass(21, 20);
		setGrass(21, 21);
		setGrass(21, 22);
		setGrass(21, 23);
		setGrass(21, 24);
		setGrass(21, 25);
		setGrass(21, 26);
		
		setGrass(25, 20);
		setGrass(25, 21);
		setGrass(25, 22);
		setGrass(25, 23);
		setGrass(25, 24);
		setGrass(25, 25);
		setGrass(25, 26);

		setItem(new FishingPole("Fishing Pole", Category.HOLD_ITEM), 23,23); 
		
		setWater(29, 29);
		
		setBush(29, 30);
		setBush(30, 29);
		setBush(30, 30);



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
		for (int i=3; i<16; i++){
			for (int j=20; j<32; j++){
				if (i % 2 == 0){
					setBush(i, j);
				}
			}
		}
		
		// map2.3: bottom left (17,32), (0, 16)
		for (int i=20; i<32; i++){
			for (int j=0; j<16; j++){
				if (j % 2 == 0){
					setWater(i, j);
				}
			}
		}

		// map2.4: bottom right (17,32),(17,32)
		for (int i=17; i<32; i++){
			for (int j=17; j<32; j++){
				if (i==26 && j==26){
					setItem(new FishingPole("Fishing Pole", Category.HOLD_ITEM), 26,26); 
				}else{
					setGrass(i, j);
				}
			}
		}
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
	public Point moveable(Direction direction){
		int tempI = 0;
		int tempJ = 0;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){				
					if (direction.equals(Direction.NORTH)){
						tempI = i - 1;
						tempJ = j;
					}
					if (direction.equals(Direction.EAST)){
						tempI = i;
						tempJ = j + 1;
					}
					if (direction.equals(Direction.SOUTH)){
						tempI = i + 1;
						tempJ = j;	
					}
					if (direction.equals(Direction.WEST)){
						tempI = i;
						tempJ = j - 1;
					}
					if(!checkMoveable(tempI, tempJ)){
						return null;
					}
				}
			}
		}
		return new Point(tempI, tempJ);
	}
	
	// can I move there
	private boolean checkMoveable(int tempI, int tempJ){
		if (movementIsWithinBounds(tempI,tempJ)){ 
			if (map[tempI][tempJ].equals("B")){ 
				return false;
			}
			if(map[tempI][tempJ].equals("W")){
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
			// Let's make it 10%!
			Random generator = new Random(); 
			int random = generator.nextInt(10) + 1; // 1-10			
			if (random % 10 == 0){
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
		return mapItems[i][j];
	}
	
	public void removeItemAt(int i, int j){
		mapItems[j][i] = null;
	}
}
