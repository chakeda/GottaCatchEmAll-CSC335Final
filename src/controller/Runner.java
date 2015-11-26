package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import model.Map;
import model.Trainer;
import view.PlayerView;

public class Runner {

	public static void main(String[] args) {

		int reload = JOptionPane.OK_OPTION;
		File f = new File("trainerSave.txt");
		if(f.exists() && !f.isDirectory()) {
            reload = JOptionPane.showConfirmDialog(null, "Would you like to start from your previous save?", "Reload?", JOptionPane.OK_CANCEL_OPTION);
            if (reload == JOptionPane.OK_OPTION){
				try{
					FileInputStream fis1 = new FileInputStream("mapSave.txt");
					ObjectInputStream ois1 = new ObjectInputStream(fis1);
					Map savedMap = (Map) ois1.readObject();
					ois1.close(); 
					fis1.close();
					
					FileInputStream fis2 = new FileInputStream("trainerSave.txt");
					ObjectInputStream ois2 = new ObjectInputStream(fis2);
					Trainer savedTrainer = (Trainer) ois2.readObject();
					ois2.close();
					fis2.close();
					
					// create object by passing these params 
		      		PlayerView window = new PlayerView(savedMap, savedTrainer);
		    	    window.setVisible(true);
		    	    
				}catch(Exception e){
					e.printStackTrace();
				}
            }else{
      		  PlayerView window = new PlayerView(null, null);
    	      window.setVisible(true);
            }
		}else{
  		  PlayerView window = new PlayerView(null, null);
	      window.setVisible(true);
		}
  }

}
