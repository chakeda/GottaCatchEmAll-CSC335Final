package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SongPlayer {
	
    InputStream in;
    AudioStream audioStream;

	
	public void playMainMusic(){
		playSong("overworld.mp3");
	}
	
	public void playCongratsMusic(){
		playSong("congrats.wav");
	}
	
	public void playBattleMusic(){
		playSong("battle.mp3");
	}
	
	private void playSong(String songName){
		stopMusic();
		try {
			in = new FileInputStream(songName);
			audioStream = new AudioStream(in);
			AudioPlayer.player.start(audioStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic(){
		AudioPlayer.player.stop(audioStream);
	}

}
