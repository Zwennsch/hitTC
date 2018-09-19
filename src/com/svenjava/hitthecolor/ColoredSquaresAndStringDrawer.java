package com.svenjava.hitthecolor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ColoredSquaresAndStringDrawer implements MouseListener {
	
	private GamePlay gamePlay;
	private GamePanel panel;
	
	public final int sizeOfSquare = 70;
	private final int xPosOfSquaresLeft = GamePanel.dim.width/4 - sizeOfSquare/2;
	private final int xPosOfSquaresRight = GamePanel.dim.width/4*3 - sizeOfSquare/2;
	private final int yPosOfSquareTop = GamePanel.dim.height/4 - sizeOfSquare/2;
	private final int yPosOfSquareBottom = GamePanel.dim.height/3*2 - sizeOfSquare/2;
//	private Point positionSquare1 = new Point(xPosOfSquaresLeft, yPosOfSquareTop);
//	private Point positionSquare2 = new Point(xPosOfSquaresRight, yPosOfSquareTop);
//	private Point positionSquare3 = new Point(xPosOfSquaresLeft, yPosOfSquareBottom);
//	private Point positionSquare4 = new Point(xPosOfSquaresRight, yPosOfSquareTop);
//	
	private Rectangle square1 = new Rectangle(xPosOfSquaresLeft, yPosOfSquareTop, sizeOfSquare, sizeOfSquare);
	private Rectangle square2 = new Rectangle(xPosOfSquaresRight, yPosOfSquareTop, sizeOfSquare, sizeOfSquare);
	private Rectangle square3 = new Rectangle(xPosOfSquaresLeft, yPosOfSquareBottom, sizeOfSquare, sizeOfSquare);
	private Rectangle square4 = new Rectangle(xPosOfSquaresRight, yPosOfSquareBottom, sizeOfSquare, sizeOfSquare);
	private Font myFont = new Font("Serif", Font.BOLD, 20);
	
	private List<Rectangle> squares = new ArrayList<>();
	
	
	public ColoredSquaresAndStringDrawer(GamePlay gPlay,GamePanel panel) {
		this.gamePlay = gPlay;
		this.panel = panel;
		squares.add(square1);
		squares.add(square2);
		squares.add(square3);
		squares.add(square4);
		
	}


	public void paintSquaresAndString(Graphics g, GamePlay gp) {
		g.setColor(gp.getColors().get(0));
		g.fillRect(xPosOfSquaresLeft, yPosOfSquareTop, sizeOfSquare, sizeOfSquare);
		g.setColor(gp.getColors().get(1));
		g.fillRect(xPosOfSquaresRight, yPosOfSquareTop, sizeOfSquare, sizeOfSquare);
		g.setColor(gp.getColors().get(2));
		g.fillRect(xPosOfSquaresLeft, yPosOfSquareBottom, sizeOfSquare, sizeOfSquare);
		g.setColor(gp.getColors().get(3));
		g.fillRect(xPosOfSquaresRight, yPosOfSquareBottom, sizeOfSquare, sizeOfSquare);
		
//		draw the String in the special color
		g.setColor(gp.getRandomStringColor());
		g.setFont(myFont);
		g.drawString(gp.getRandomColorName(), GamePanel.dim.width/2 -35, GamePanel.dim.height/2 -10);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(getClickedRecNr(e) != -1) {
			if(gamePlay.getColors().get(getClickedRecNr(e)) == Color.GREEN && gamePlay.getRandomColorName() == "GREEN") {
				generateNewScreenAndSetPoints();
			}
			else if(gamePlay.getColors().get(getClickedRecNr(e)) == Color.RED && gamePlay.getRandomColorName() == "RED") {
				generateNewScreenAndSetPoints();
			}
			else if(gamePlay.getColors().get(getClickedRecNr(e)) == Color.YELLOW && gamePlay.getRandomColorName() == "YELLOW") {
				generateNewScreenAndSetPoints();
			}
			else if(gamePlay.getColors().get(getClickedRecNr(e)) == Color.BLUE && gamePlay.getRandomColorName() == "BLUE") {
				generateNewScreenAndSetPoints();
			}
			else {
				gamePlay.gameOver(panel);
			}
			if(!GamePlay.gameOver) {
				panel.repaint();
				
			}
		}
	}


	private void generateNewScreenAndSetPoints() {
		if(gamePlay.time >0)
		GamePlay.points++;
		gamePlay.shuffleColorsAndNames();
		gamePlay.setRandomColorName();
		gamePlay.initRandomStringColor();
		gamePlay.gameTimer.cancel();
		gamePlay.restartGameTimer(panel);
	}


	private int getClickedRecNr(MouseEvent e) {
		if(square1.contains(e.getPoint())) {
			return 0;
		}
		else if(square2.contains(e.getPoint())) {
			return 1;
		}
		else if(square3.contains(e.getPoint())) {
			return 2;
		}
		else if(square4.contains(e.getPoint())) {
			return 3;
		}
		else return -1;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
