package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.awt.AWTUtilities;

import controller.SongPlayer;
import model.Direction;
import model.Map;

public class MapView extends JPanel implements Observer {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Map map;
	private Image plain, grass, bush, pokeball, water;
	private Image playerForward1, playerForward2, playerForward3, playerLeft1, playerLeft2, playerLeft3, playerBack1,
			playerBack2, playerBack3, playerRight1, playerRight2, playerRight3;
	private Image costumeForward1, costumeForward2, costumeForward3, costumeLeft1, costumeLeft2, costumeLeft3,
			costumeBack1, costumeBack2, costumeBack3, costumeRight1, costumeRight2, costumeRight3;
	private int X, Y, tic, n;
	private Direction direction;
	private Timer timer;
	private boolean costumeFlag = true;

	public MapView(Map map) {
		this.map = map;
		X = this.map.getTrainerX() * 16;
		Y = this.map.getTrainerY() * 16;
		try {
			playerForward1 = ImageIO.read(new File("./images/trainerImages/trainerForward1.png"));
			playerForward2 = ImageIO.read(new File("./images/trainerImages/trainerForward2.png"));
			playerForward3 = ImageIO.read(new File("./images/trainerImages/trainerForward3.png"));
			playerLeft1 = ImageIO.read(new File("./images/trainerImages/trainerLeft1.png"));
			playerLeft2 = ImageIO.read(new File("./images/trainerImages/trainerLeft2.png"));
			playerLeft3 = ImageIO.read(new File("./images/trainerImages/trainerLeft3.png"));
			playerBack1 = ImageIO.read(new File("./images/trainerImages/trainerBack1.png"));
			playerBack2 = ImageIO.read(new File("./images/trainerImages/trainerBack2.png"));
			playerBack3 = ImageIO.read(new File("./images/trainerImages/trainerBack3.png"));
			playerRight1 = ImageIO.read(new File("./images/trainerImages/trainerRight1.png"));
			playerRight2 = ImageIO.read(new File("./images/trainerImages/trainerRight2.png"));
			playerRight3 = ImageIO.read(new File("./images/trainerImages/trainerRight3.png"));

			plain = ImageIO.read(new File("./images/plain.png"));
			grass = ImageIO.read(new File("./images/grass.png"));
			bush = ImageIO.read(new File("./images/bush.png"));
			pokeball = ImageIO.read(new File("./images/pokeball.png"));
			water = ImageIO.read(new File("./images/water.gif"));

			costumeForward1 = ImageIO.read(new File("./images/costumeChange/rubyFront1.png"));
			costumeForward2 = ImageIO.read(new File("./images/costumeChange/rubyFront2.png"));
			costumeForward3 = ImageIO.read(new File("./images/costumeChange/rubyFront3.png"));
			costumeLeft1 = ImageIO.read(new File("./images/costumeChange/rubyLeft1.png"));
			costumeLeft2 = ImageIO.read(new File("./images/costumeChange/rubyLeft2.png"));
			costumeLeft3 = ImageIO.read(new File("./images/costumeChange/rubyLeft3.png"));
			costumeBack1 = ImageIO.read(new File("./images/costumeChange/rubyBack1.png"));
			costumeBack2 = ImageIO.read(new File("./images/costumeChange/rubyBack2.png"));
			costumeBack3 = ImageIO.read(new File("./images/costumeChange/rubyBack3.png"));
			costumeRight1 = ImageIO.read(new File("./images/costumeChange/rubyRight1.png"));
			costumeRight2 = ImageIO.read(new File("./images/costumeChange/rubyRight2.png"));
			costumeRight3 = ImageIO.read(new File("./images/costumeChange/rubyRight3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// call it to initiate
		timer = new Timer(40, new TimerListener());
		repaint();
	}

	// repaints with animation
	@Override
	public void update(Observable observable, Object dir) {
		map = (Map) observable;
		direction = (Direction) dir;
		drawBoardWithAnimation();
	}

	// timer based repaints
	private void drawBoardWithAnimation() {
		if(direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)){
			n = 8;
		}
		else{
			n = 9;
		}
		tic = 1;
		timer.start();
	}

	// called by repaint -- paint errthang
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// we're going to build and draw this.
		Image[][] imageGrid = new Image[map.getMapLength()][map.getMapLength()];

		// tester
		for (int i = 0; i < map.getMapLength(); i++) {
			for (int j = 0; j < map.getMapLength(); j++) {
				// System.out.print(map.getTileAt(i, j));
			}
			// System.out.println();
		}

		// start with terrain. find terrain
		for (int i = 0; i < map.getMapLength(); i++) {
			for (int j = 0; j < map.getMapLength(); j++) {

				if (map.getTileAt(i, j).equals("G")) {
					imageGrid[i][j] = grass;
				} else if (map.getTileAt(i, j).equals("B")) {
					imageGrid[i][j] = bush;
				} else if (map.getTileAt(i, j).equals("I")) {
					imageGrid[i][j] = pokeball;
				} else if (map.getTileAt(i, j).equals("W")) {
					imageGrid[i][j] = water;
				} else {
					imageGrid[i][j] = plain;
				}
			}
		}

		// set all tiles to plain, so you can draw items
		// draw the current active map
		// then, display the dude. always on top

		for (int r = 0; r < 512; r += 16) {
			for (int c = 0; c < 512; c += 16) {
				g2.drawImage(plain, r, c, null);
			}
		}

		if (X < 256 && Y < 256) {
			// top left
			for (int r = 0; r < 256; r += 16) {
				for (int c = 0; c < 256; c += 16) {
					g2.drawImage(imageGrid[c / 16][r / 16], r, c, null);
				}
			}
			if (direction == Direction.SOUTH) {
				if (tic < 4) {
					g2.drawImage(playerForward2, X, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerForward3, X, Y, null);
				} else {
					g2.drawImage(playerForward1, X, Y, null);
				}
			} else if (direction == Direction.EAST) {
				if (tic < 4) {
					g2.drawImage(playerRight2, X, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerRight3, X, Y, null);
				} else {
					g2.drawImage(playerRight1, X, Y, null);
				}
			} else if (direction == Direction.NORTH) {
				if (tic < 4) {
					g2.drawImage(playerBack2, X, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerBack3, X, Y, null);
				} else {
					g2.drawImage(playerBack1, X, Y, null);
				}
			} else if (direction == Direction.WEST) {
				if (tic < 4) {
					g2.drawImage(playerLeft2, X, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerLeft3, X, Y, null);
				} else {
					g2.drawImage(playerLeft1, X, Y, null);
				}
			} else {
				g2.drawImage(playerForward1, X, Y, null);
			}

		} else if (X > 256 && Y < 256) {
			// top right
			for (int r = 256; r < 512; r += 16) {
				for (int c = 0; c < 256; c += 16) {
					g2.drawImage(imageGrid[c / 16][r / 16], r - 256, c, null);
				}
			}
			if (direction == Direction.SOUTH) {
				if (tic < 4) {
					g2.drawImage(playerForward2, X - 256, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerForward3, X - 256, Y, null);
				} else {
					g2.drawImage(playerForward1, X - 256, Y, null);
				}
			} else if (direction == Direction.EAST) {
				if (tic < 4) {
					g2.drawImage(playerRight2, X - 256, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerRight3, X - 256, Y, null);
				} else {
					g2.drawImage(playerRight1, X - 256, Y, null);
				}
			} else if (direction == Direction.NORTH) {
				if (tic < 4) {
					g2.drawImage(playerBack2, X - 256, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerBack3, X - 256, Y, null);
				} else {
					g2.drawImage(playerBack1, X - 256, Y, null);
				}
			} else if (direction == Direction.WEST) {
				if (tic < 4) {
					g2.drawImage(playerLeft2, X - 256, Y, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerLeft3, X - 256, Y, null);
				} else {
					g2.drawImage(playerLeft1, X - 256, Y, null);
				}
			} else {
				g2.drawImage(playerForward1, X - 256, Y, null);
			}

		} else if (X < 256 && Y > 256) {
			// bottom left
			for (int r = 0; r < 256; r += 16) {
				for (int c = 256; c < 512; c += 16) {
					g2.drawImage(imageGrid[c / 16][r / 16], r, c - 256, null);
				}
			}
			if (direction == Direction.SOUTH) {
				if (tic < 4) {
					g2.drawImage(playerForward2, X, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerForward3, X, Y - 256, null);
				} else {
					g2.drawImage(playerForward1, X, Y - 256, null);
				}
			} else if (direction == Direction.EAST) {
				if (tic < 4) {
					g2.drawImage(playerRight2, X, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerRight3, X, Y - 256, null);
				} else {
					g2.drawImage(playerRight1, X, Y - 256, null);
				}
			} else if (direction == Direction.NORTH) {
				if (tic < 4) {
					g2.drawImage(playerBack2, X, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerBack3, X, Y - 256, null);
				} else {
					g2.drawImage(playerBack1, X, Y - 256, null);
				}
			} else if (direction == Direction.WEST) {
				if (tic < 4) {
					g2.drawImage(playerLeft2, X, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerLeft3, X, Y - 256, null);
				} else {
					g2.drawImage(playerLeft1, X, Y - 256, null);
				}
			} else {
				g2.drawImage(playerForward1, X, Y - 256, null);
			}

		} else if (X > 256 && Y > 256) {
			// bottom right
			for (int r = 256; r < 512; r += 16) {
				for (int c = 256; c < 512; c += 16) {
					g2.drawImage(imageGrid[c / 16][r / 16], r - 256, c - 256, null);
				}
			}
			if (direction == Direction.SOUTH) {
				if (tic < 4) {
					g2.drawImage(playerForward2, X - 256, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerForward3, X - 256, Y - 256, null);
				} else {
					g2.drawImage(playerForward1, X - 256, Y - 256, null);
				}
			} else if (direction == Direction.EAST) {
				if (tic < 4) {
					g2.drawImage(playerRight2, X - 256, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerRight3, X - 256, Y - 256, null);
				} else {
					g2.drawImage(playerRight1, X - 256, Y - 256, null);
				}
			} else if (direction == Direction.NORTH) {
				if (tic < 4) {
					g2.drawImage(playerBack2, X - 256, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerBack3, X - 256, Y - 256, null);
				} else {
					g2.drawImage(playerBack1, X - 256, Y - 256, null);
				}
			} else if (direction == Direction.WEST) {
				if (tic < 4) {
					g2.drawImage(playerLeft2, X - 256, Y - 256, null);
				} else if (tic > 4 && tic < 8) {
					g2.drawImage(playerLeft3, X - 256, Y - 256, null);
				} else {
					g2.drawImage(playerLeft1, X - 256, Y - 256, null);
				}
			} else {
				g2.drawImage(playerForward1, X - 256, Y - 256, null);
			}

		}
		
		if(battleAnimationFlag){
			g2.setColor(getBackground());
			g2.fillRect(0, 0, 256, 256);
		}
	}
	
	/*public void moveTrainer(Direction d){
		direction = d;
		drawBoardWithAnimation();
	}*/

	// move around
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tic < n) {
				if (direction == Direction.NORTH) {
					Y = Y - 2;
					repaint();
					tic++;
				}
				if (direction == Direction.WEST) {
					X = X - 2;
					repaint();
					tic++;
				}
				if (direction == Direction.SOUTH) {
					Y = Y + 2;
					repaint();
					tic++;
				}
				if (direction == Direction.EAST) {
					X = X + 2;
					repaint();
					tic++;
				}
			} else {
				timer.stop();
			}

		}
	}

	public void speedUpTrainer() {
		timer = new Timer(1, new TimerListener());
	}

	public void changeCostume() {
		if (costumeFlag) {
			playerForward1 = costumeForward1;
			playerForward2 = costumeForward2;
			playerForward3 = costumeForward3;
			playerLeft1 = costumeLeft1;
			playerLeft2 = costumeLeft2;
			playerLeft3 = costumeLeft3;
			playerBack1 = costumeBack1;
			playerBack2 = costumeBack2;
			playerBack3 = costumeBack3;
			playerRight1 = costumeRight1;
			playerRight2 = costumeRight2;
			playerRight3 = costumeRight3;

		} else {
			try {
				playerForward1 = ImageIO.read(new File("./images/trainerImages/trainerForward1.png"));
				playerForward2 = ImageIO.read(new File("./images/trainerImages/trainerForward2.png"));
				playerForward3 = ImageIO.read(new File("./images/trainerImages/trainerForward3.png"));
				playerLeft1 = ImageIO.read(new File("./images/trainerImages/trainerLeft1.png"));
				playerLeft2 = ImageIO.read(new File("./images/trainerImages/trainerLeft2.png"));
				playerLeft3 = ImageIO.read(new File("./images/trainerImages/trainerLeft3.png"));
				playerBack1 = ImageIO.read(new File("./images/trainerImages/trainerBack1.png"));
				playerBack2 = ImageIO.read(new File("./images/trainerImages/trainerBack2.png"));
				playerBack3 = ImageIO.read(new File("./images/trainerImages/trainerBack3.png"));
				playerRight1 = ImageIO.read(new File("./images/trainerImages/trainerRight1.png"));
				playerRight2 = ImageIO.read(new File("./images/trainerImages/trainerRight2.png"));
				playerRight3 = ImageIO.read(new File("./images/trainerImages/trainerRight3.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		costumeFlag = !costumeFlag;
		repaint();
	}
	
	/**intro battle animation stuff is below**/

	private boolean battleAnimationFlag = false;
	private Timer battleAnimationTimer = new Timer(125, new BattleAnimationListener(this));
	private int tic2 = 0;
	private PlayerView view;
	public void beginBattleAnimation(PlayerView v){
		view = v;
		battleAnimationTimer.start();
	}
	
	
	private class BattleAnimationListener implements ActionListener{
		MapView map;
		
		public BattleAnimationListener(MapView thisMap){
			map = thisMap;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(tic2 < 15){
				battleAnimationFlag =! battleAnimationFlag;
				tic2++;
			}
			else{
				battleAnimationTimer.stop();
				battleAnimationFlag = false;
				tic2 = 0;
				view.beginBattle();
			}
			map.repaint();
		}
		
	}
	
}
