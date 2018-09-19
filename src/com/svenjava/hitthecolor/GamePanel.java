package com.svenjava.hitthecolor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener {
    static final Dimension dim = new Dimension(360, 640);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    GamePlay gPlay;
    ColoredSquaresAndStringDrawer csd;
    JButton startButton = new JButton();
    int counter = 0;
    boolean tester = false;

    GamePanel() {
        gPlay = new GamePlay();
        csd = new ColoredSquaresAndStringDrawer(gPlay, this);
        GamePlay.isRunning = false;
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        startButton.addActionListener(this);
        this.addMouseListener(csd);
    }

    public Dimension getPreferredSize() {
        return new Dimension(dim);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBasicInfo(g);
        if (!GamePlay.isRunning) {
            drawFirstScreen(g);
        }
        if (GamePlay.isRunning && !GamePlay.gameOver) {
//			gPlay.shuffleColorsAndNames();
            csd.paintSquaresAndString(g, gPlay);
        }
        if (GamePlay.gameOver) {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(0xde2384));
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Game Over!", dim.width / 2 - 70, dim.height / 2 - 100);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 10));
        g.drawString("Push Button to Start an new game", dim.width / 2 - 100, dim.height / 2 - 80);
    }

    private void drawBasicInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, dim.width / 3, dim.height / 30);
        g.fillRect(dim.width - dim.width / 3, 0, dim.width / 3, dim.height / 30);
        g.fillRect(dim.width / 2 - 50, dim.height - dim.height / 30, 100, dim.height / 30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 10));
        g.drawString("Score: " + GamePlay.points, 8, 12);
        g.drawString("Level: " + GamePlay.level, dim.width - dim.width / 3 + 8, 12);
        g.drawString("Time: " + gPlay.time + " seconds", dim.width / 2 - 50 + 8, dim.height - dim.height / 30 + 12);
        drawFirstScreen(g);
    }

    private void drawFirstScreen(Graphics g) {
        if (GamePlay.gameOver) {
            startButton.setEnabled(true);
            startButton.setVisible(true);
            gPlay.time = 5;
            GamePlay.points = 0;
            GamePlay.level = 1;
        }
        startButton.setLayout(null);
        startButton.setText("Start");
        startButton.setBounds(dim.width / 2 - startButton.getWidth() / 2, dim.height / 2 - startButton.getHeight() / 2, startButton.getWidth(), startButton.getHeight());

        this.add(startButton, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GamePlay.isRunning = true;
        GamePlay.gameOver = false;
        gPlay.restartGameTimer(this);
        startButton.setEnabled(false);
        startButton.setVisible(false);
        repaint();
    }
}
