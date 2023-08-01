package it.unibo.graphics.impl;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.model.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private GameState gameState;

    public GameOverPanel(GameState gameState) {
        this.gameState = gameState;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = new JLabel("Gioco finito!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel pointsLabel = new JLabel("Points: " + gameState.getScore());
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 25));

        add(gameOverLabel);
        add(pointsLabel);
    }
}  
