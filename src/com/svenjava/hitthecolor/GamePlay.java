package com.svenjava.hitthecolor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePlay {
	
	static boolean isRunning = false;
	static boolean gameOver = false;
	static Random rand = new Random();
	private List<Color> colors = new ArrayList<>();
	private List<String> colorNames = new ArrayList<>();
	private String randomColorName;
	public static int points;
	public static int level;
	public int time;
	protected Timer gameTimer;
	private Color randomStringColor;
	
	
	public GamePlay() {
		isRunning = true;
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.red);
		colors.add(Color.yellow);
		
		colorNames.add("GREEN");
		colorNames.add("YELLOW");
		colorNames.add("BLUE");
		colorNames.add("RED");
		
		shuffleColorsAndNames();
		setRandomColorName();
		points= 0;
		level = 1;
		time = 5;
	}
	
	public void restartGameTimer(GamePanel panel) {
		
		gameTimer = new Timer();
		time = 5;
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Seconds: "+ time);
				time--;
				if(time <= 0) {
					GamePlay.gameOver = true;
					gameTimer.cancel();
				}
				panel.repaint(GamePanel.dim.width/2 -50, GamePanel.dim.height-GamePanel.dim.height/30, 100, GamePanel.dim.height/30);;
			}
		}, 1000, 1000);
	}

	public void shuffleColorsAndNames() {
		if (isRunning) {
			Collections.shuffle(colors);
			Collections.shuffle(colorNames);
		}
	}
	
	public void setRunning(boolean isRunning) {
		GamePlay.isRunning = isRunning;
	}
	
	public List<Color> getColors() {
		return colors;
	}



	public String getRandomColorName() {
		return randomColorName;
	}



	public void setRandomColorName() {
		this.randomColorName = colorNames.get(rand.nextInt(colorNames.size()));
	}



	public void gameOver(GamePanel panel) {
		gameOver = true;
		isRunning = false;
		panel.repaint();
		gameTimer.cancel();
	}

	public Color getRandomStringColor() {
		return randomStringColor;
	}

	public void setRandomStringColor() {
		this.randomStringColor = this.getColors().get(new Random().nextInt(getColors().size()));
	}


}
