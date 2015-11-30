
/**
 * 
 * Authors - Jared Worthington & Kite Christianson
 * 
 * 
 * This class is a small part of Rick Mercer's Cashless Jukebox System.
 * 
 * This Song type keeps the song title and file name of its audio file.
 * It also has instance variables and methods to allow users to prevent
 * any Song object from being played more than twice a day.
 *  
 * @author Rick Mercer
 */

package songplayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Song implements Serializable{

        private String fileName;
        
        public Song(final String fileName) {
        	this.fileName = fileName;
        }
        
	    public String getAudioFileName() {
	    	return fileName;
	    }
 
}
