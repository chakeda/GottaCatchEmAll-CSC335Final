/*
 * 
 * Authors: Kite Christianson & Jared Worthington
 * 
 * 
 * This class is a part of the cash-less jukebox system
 * 
 * The playlist object contains a list of valid songs which CAN BE played, a queue which contains songs TO BE played, and a
 * queue playing waiter which watches for the end of a song and iterates through the song queue
 * 
 * Local methods include:
 * isValidSong() - checks to make sure that the jukebox can play the selected song.
 * queueUpNextSong()- adds a song to the song queue and plays it if the song queue was previously empty
 * 
 * embedded classes include:
 * 		QueuePlayingWaiter()- this class implements EndOfSongListener and uses an event (eos)"end of song" to look for and iterate the song queue
 * 				local methods include:
 * 				iterateSongQueue() - once called this method removes the previously played song off the queue, waits 2 seconds and 
 * 									plays the next song if there is a song waiting.
 * 
 */
package songplayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JFrame;

import songplayer.*;
import view.*;

public class PlayList implements Serializable{
	
	Song songA = new Song("./sounds/main.mp3");
	
	private ArrayList<Song> songList; 
	private static Queue<Song> songQueue;
	private static QueuePlayingWaiter waiter;

	public PlayList() {
	    songList = new ArrayList<Song>();
	    songList.add(songA);
	    //songList.add(songB);
	    //songList.add(songC);
	    
	    waiter = new QueuePlayingWaiter();
	    songQueue = new LinkedList<Song>();
	}
	
	public PlayList(String empty){
	    songList = new ArrayList<Song>();
	    waiter = new QueuePlayingWaiter();
	    songQueue = new LinkedList<Song>();
	}
	
	public Queue<Song> getSongQueue(){
		return songQueue;
	}
	
	public QueuePlayingWaiter getWaiter(){
		return waiter;
	}
	
	public ArrayList<Song> getSongList(){
		return this.songList;
	}
	
	
	// queues a song. if its the first time called, begin the queue player
	public void queueUpNextSong(Song song){	
		songQueue.add(song);
		if (songQueue.size() == 1){
			SongPlayer.playFile(waiter, song.getAudioFileName());
		}
	}
	
	// delete all songs
	public void endCurrentSong(){
		songQueue.poll();
		songQueue.clear();
	}
    
	// this listener is the mechanism that plays the whole music queue
    public class QueuePlayingWaiter implements EndOfSongListener {
    	
    	private boolean completion;
    	
        // once the song finishes, iterate the queue
    	public void songFinishedPlaying(EndOfSongEvent eosEvent) {
    	
    		try {
				iterateSongQueue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		completion = notifyCompletion();
    	}
    	
        // remove top, sleep 2s, play next, once song finishes, iterate the queue
        public void iterateSongQueue() throws InterruptedException{
    		songQueue.remove();
			
			//Thread.sleep(2000); // sleeps whole game

			Song nextSong = songQueue.peek();
			if (nextSong != null){
				SongPlayer.playFile(waiter, nextSong.getAudioFileName());
			}
        }
        
        public boolean notifyCompletion(){
        	return true;
        }
        
        public boolean getCompletion(){
        	return completion;
        }
        
    }
    


}

