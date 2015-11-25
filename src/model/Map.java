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
		setTrainer(0,5); // trainer starts here
		setGrass(0,0);
		setGrass(0,1);
		setGrass(1,0);
		setGrass(2,0);
		setGrass(2,1);
		setGrass(4,0);
		setGrass(5,0);
		setGrass(4,1);
		setGrass(5,1);
		setGrass(4,2);
		setGrass(5,2);
		setGrass(4,3);
		setGrass(5,3);
		setGrass(4,4);
		setGrass(5,5);
		setGrass(4,5);
		setGrass(5,4);
		setGrass(6,0);
		setGrass(6,1);
		setGrass(7,0);
		setGrass(7,1);
		setGrass(8,0);
		setGrass(8,1);
		setGrass(9,0);
		setGrass(6,2);
		setGrass(7,2);
		setGrass(8,2);
		setGrass(9,2);
		setGrass(6,3);
		setGrass(7,3);
		setGrass(8,3);
		setGrass(9,3);
		setGrass(6,4);
		setGrass(7,4);
		setGrass(8,4);
		setGrass(9,4);
		setGrass(6,5);
		setGrass(7,5);
		setGrass(8,5);
		setGrass(9,5);
		setGrass(6,6);
		setGrass(7,6);
		setGrass(8,6);
		setGrass(9,6);
		setGrass(6,6);
		setGrass(9,1);
		setGrass(4,6);
		setGrass(5,6);
		setBush(3,0);
		setBush(3,1);
		setBush(3,2);
		setBush(3,3);
		setBush(3,4);
		setBush(3,5);
		setBush(3,6);
		setBush(3,7);
		setBush(7,7);
		setBush(3,7);
		setBush(4,7);
		setBush(5,7);
		setBush(6,7);
		setBush(7,7);
		setBush(8,7);
		setBush(9,7);
		setBush(10,7);
		setBush(11,7);
		setBush(12,7);
		setBush(13,7);
		
		setBush(1,1);
		setBush(1,2);
		setBush(0,2);
		setBush(0,7);
		setGrass(10,0);
		setGrass(11,0);
		setGrass(12,0);
		setGrass(13,0);
		setGrass(10,1);
		setGrass(11,1);
		setGrass(12,1);
		setGrass(13,1);
		setGrass(10,2);
		setGrass(11,2);
		setGrass(12,2);
		setGrass(13,2);
		setGrass(10,3);
		setGrass(11,3);
		setGrass(12,3);
		setGrass(13,3);
		setGrass(10,4);
		setGrass(11,4);
		setGrass(12,4);
		setGrass(13,4);
		setGrass(10,5);
		setGrass(11,5);
		setGrass(12,5);
		setGrass(13,5);
		setGrass(10,6);
		setGrass(11,6);
		setGrass(12,6);
		setGrass(13,6);
		
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
		setGrass(0,17);
		setGrass(0,18);
		setGrass(0,21);
		setGrass(0,22);
		setGrass(0,23);
		setGrass(0,24);
		setGrass(0,25);
		setGrass(0,26);
		setGrass(0,27);
		setGrass(0,28);
		setGrass(0,29);
		setGrass(0,30);
		setGrass(0,31);
		setGrass(1,17);
		setGrass(1,18);
		setGrass(1,19);
		setGrass(1,22);
		setGrass(1,21);
		setGrass(1,23);
		setGrass(1,24);
		setGrass(1,25);
		setGrass(1,26);
		setGrass(1,27);
		setGrass(1,28);
		setGrass(1,29);
		setGrass(1,30);
		setGrass(1,31);
		setGrass(2,17);
		setGrass(2,18);
		setGrass(2,21);
		setGrass(2,22);
		setGrass(2,23);
		setGrass(2,24);
		setGrass(2,25);
		setGrass(2,26);
		setGrass(2,27);
		setGrass(2,28);
		setGrass(2,29);
		setGrass(2,30);
		setGrass(2,31);
		setWater(10,22);
		setWater(9,22);
		setWater(10,23);
		setWater(9,23);
		
		
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
		setWater(31,11);
		setWater(30,11);
		setWater(30,12);
		setWater(31,12);
		setWater(30,13);
		setWater(31,13);
		setWater(30,14);
		setWater(31,14);
		setWater(30,15);
		setWater(31,15);
		setWater(30,16);
		setWater(31,16);
		setWater(30,17);
		setWater(31,17);
		setWater(30,18);
		setWater(31,18);
		setWater(30,19);
		setWater(31,19);
		setWater(30,20);
		setWater(31,20);
		setWater(30,21);
		setWater(31,21);
		setWater(29,11);
		setWater(29,12);
		setWater(29,13);
		setWater(29,14);
		setWater(29,15);
		setWater(29,16);
		setWater(29,17);
		setWater(29,18);
		setWater(29,19);
		setWater(29,20);
		setWater(29,21);
	
		
		
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
		setWater(22,31);
		setWater(23,31);
		setWater(22,30);
		setWater(23,30);
	}
	
	// sets grass
	public void setGrass(int k, int j){
		map[k][j] = "G";
	}
	
	// sets bush (unwalkable terrain)
	public void setBush(int k, int j){
		map[k][j] = "B";
	}
	
	public void setWater(int k, int j){
		map[k][j]= "W";
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
							if(map[tempI][tempJ].equals("W")){
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
