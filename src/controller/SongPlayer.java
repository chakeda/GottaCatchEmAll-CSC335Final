package controller;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class SongPlayer {
	
    InputStream in;
    AudioStream audioStream;

	
	public void playMainMusic(){
		playSong("overworld.mp3");
	}
	
	public void playCongratsMusic(){
		playSong("congrats.wav");
	}
	
	public void playPokemonCaughtMusic(){
		playSong("pokemonCaught.mp3");
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
	
	public void playRunAwaySoundEffect(){
		SongPlayer sp = new SongPlayer();
		sp.playRunAway();
	}
	
	private void playRunAway(){
		playSong("runAway.mp3");
	}
	
	public void startMusic(){
		AudioPlayer.player.start(audioStream);
	}

}
