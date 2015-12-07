package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import model.Item;
import model.Pokemon;

public class ItemView {
	private Pokemon pokemon;
	private Item item;
	private PlayerView pv;
	private Timer timer;
	private int tic, time, itemX, itemY;
	private Image pokemonImage, itemImage;
	private JFrame frame;
	private JLabel pokemonImageLabel, itemImageLabel;

	public ItemView(String pok, String it) {
		itemX = 100;
		itemY = 100;
		time = 200;
		tic = 0;
		frame = new JFrame("Item Fun");
		// timer = new Timer(40, new ItemListener());
		// timer.start();
		String pokemonFileName = pok.toLowerCase() + ".png";
		try {
			pokemonImage = ImageIO.read(new File("./images/pokemonImages/"
					+ pokemonFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (it.equals("Lemonade")) {
			try {
				//System.out.println("In lemoande iff");
				itemImage = ImageIO.read(new File("./images/lemon.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (it.equals("Energy Root")) {
			try {
				//System.out.println("In energy iff");
				itemImage = ImageIO.read(new File("./images/EnergyRoot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (it.equals("Berry Juice")) {
			try {
				//System.out.println("In berry iff");
				itemImage = ImageIO.read(new File("./images/BerryJuice.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (it.equals("Fresh Water")) {
			try {
				System.out.println("In fresh water iff");
				itemImage = ImageIO.read(new File("./images/FreshWater2.png"));
			} catch (IOException e) {
				e.printStackTrace();
				//System.out.println("In fresh water iff not working");
			}
		}
		// frame.add(itemImage);
		//frame.setBackground(Color.WHITE);
		itemImageLabel = new JLabel(new ImageIcon(itemImage));
		frame.add(itemImageLabel);

		// poke image
		pokemonImageLabel = new JLabel(new ImageIcon(pokemonImage));
		frame.add(pokemonImageLabel);

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(250, 250);
		// frame.add(canvas);
		// frame.setLayout(new BorderLayout());
		// frame.add(new ItemListener());
		// frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// frame.repaint();

		// initial slide in anime
		ActionListener initialAnimationPerformer = new ActionListener() {
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count == 0) {
					itemImageLabel.setLocation(10,60);
					pokemonImageLabel.setLocation(10,60);
					
				}
				if (count == 100) {
					((Timer) e.getSource()).stop();
				} else {
					// and down
					//itemImageLabel.setLocation(
						//	itemImageLabel.getLocation().x + 5,
							//itemImageLabel.getLocation().y);
					count++;
				}
			}
		};
		new Timer(100, initialAnimationPerformer).start();
		/*
		 * public void paintComponent(Graphics g) { //super.paintComponent(g);
		 * Graphics2D g2 = (Graphics2D) g; //g.drawImage(boat, itemX, itemY,
		 * this); g2.drawImage(itemImage, itemX, itemY, null);
		 * 
		 * }
		 * 
		 * private class ItemListener implements ActionListener{
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { if (tic <=
		 * time) { itemY = itemY + 5; frame.repaint(); tic++; } else {
		 * timer.stop(); }
		 * 
		 * } }
		 */

	}
}
