package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

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
	private ArrayList<Item> mapItemList;
	
	// instantiate
	public Map(){
		map = new String[32][32]; // background
		mapFog = new String[32][32]; // foreground
		mapItems = new Item[32][32];
		mapItemList = new ArrayList<Item>();
		
		cleanNulls();
		
		//// statically generate map 
		
		// map1: top left (0,16), (0,16)
		setTrainer(3,3); // trainer starts here
		setGrass(4,4);
		setGrass(5,5);
		setGrass(4,5);
		setGrass(5,4);
		setBush(7,7);
		setBush(1,1);
		setBush(1,2);
		setBush(0,2);
		setBush(0,7);
		
		
		// map2: top right (0,16), (17,32)
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
		// setItem(new RunningShoes("Running Shoes", Category.HOLD_ITEM), 4,20); // breaks serialization
		
		// map3: bottom left (17,32), (0, 16)
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
		
		// map4: bottom right (17,32),(17,32)
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
		
		// add to parallel item map, store the item
		mapItemList.add(item); // add item to top
		mapItems[k][j] = mapItemList.get(mapItemList.size()-1); // get the top item
	}
	
	// sets a hunter
	public void setTrainer(int k, int j){
		mapFog[k][j] = "T";
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
						if (movementIsWithinBounds(tempI,tempJ)){ 
							if (map[tempI][tempJ].equals("B")){ 
								return false;
							}
							if (map[tempI][tempJ].equals("I")){
								map[tempI][tempJ] = ""; // turn ball to plain
								// TODO: somehow add the item into the inventory...
								return false;
							}
						}else{
							return false;
						}
					}
					if (direction.equals("right")){
						tempI = i;
						tempJ = j + 1;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ].equals("B")){
								return false;
							}
							if (map[tempI][tempJ].equals("I")){
								map[tempI][tempJ] = ""; // turn ball to plain
								// TODO: somehow add the item into the inventory...
								return false;
							}
						}else{
							return false;
						}
					}
					if (direction.equals("down")){
						tempI = i + 1;
						tempJ = j;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ].equals("B")){
								return false;
							}
							if (map[tempI][tempJ].equals("I")){
								map[tempI][tempJ] = ""; // turn ball to plain
								// TODO: somehow add the item into the inventory...
								return false;
							}
						}else{
							return false;
						}
					}
					if (direction == "left"){
						tempI = i;
						tempJ = j - 1;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ].equals("B")){
								return false;
							}
							if (map[tempI][tempJ].equals("I")){
								map[tempI][tempJ] = "G"; // turn ball to plain
								// TODO: somehow add the item into the inventory...
								return false;
							}
						}else{
							return false;
						}
					} 
				}
			}
		}
		// shouldn't get here.
		return true;
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
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){
					return j;
				}
			}
		}
		// shouldnt happen
		return 0;
	}
	
	// get Y location
	public int getTrainerY(){
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j]!=null && mapFog[i][j].equals("T")){
					return i;
				}
			}
		}
		// shouldnt happen
		return 0;
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
}
