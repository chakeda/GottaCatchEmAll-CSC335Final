package model;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Map  extends Observable{
	
	/***
	 * Instantiation and its methods
	 */
	
	//// note: making map into Object or Character causes null pointers like a mf
	private String[][] map;
	private String[][] mapFog;
	
	// instantiate
	public Map(){
		map = new String[32][32]; // background
		mapFog = new String[32][32]; // foreground
		cleanNulls();
		
		//// statically generate map 
		
		// top left (0,16), (0,16)
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
		
		// top right (0,16), (17,32)
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
		
		// bottom left (17,32), (0, 16)
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
		
		// bottom right (17,32),(17,32)
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
		String dir = null;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j] == "T"){				
					// previous space becomes map. get resultant position
					if (direction == "up"){
						tempI = i - 1;
						tempJ = j;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ] == "B"){
								return false;
							}
						}else{
							return false;
						}
					}
					if (direction == "right"){
						tempI = i;
						tempJ = j + 1;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ] == "B"){
								return false;
							}
						}else{
							return false;
						}
					}
					if (direction == "down"){
						tempI = i + 1;
						tempJ = j;
						if (movementIsWithinBounds(tempI,tempJ)){
							if (map[tempI][tempJ] == "B"){
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
							if (map[tempI][tempJ] == "B"){
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
	
	// move trainer, call movement observer. out of bounds and obstacles are 
	//     accounted for in the mapview
	public void moveTrainer(String direction){
		int tempI = 0;
		int tempJ = 0;
		String dir = null;
		for (int i=0; i<map.length; i++){
			for (int j=0; j<map.length; j++){
				if (mapFog[i][j] == "T"){				
					// previous space becomes map. get resultant position
					if (direction == "up"){
						mapFog[i][j] = map[i][j];
						tempI = i - 1;
						tempJ = j;
						dir = "up";
					}
					if (direction == "right"){
						mapFog[i][j] = map[i][j];
						tempI = i;
						tempJ = j + 1;
						dir = "right";
					}
					if (direction == "down"){
						mapFog[i][j] = map[i][j];
						tempI = i + 1;
						tempJ = j;
						dir = "down";
					}
					if (direction == "left"){
						mapFog[i][j] = map[i][j];
						tempI = i;
						tempJ = j - 1;
						dir = "left";
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
				if (mapFog[i][j] == "T"){
					return i;
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
				if (mapFog[i][j] == "T"){
					return j;
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
}
