package it.unibo.graphics.impl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.model.GameState;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * A custom JPanel representing the victory screen with game statistics.
 */
public class VictoryPanel extends JPanel {
    //private int score;
    private GameState gameState;
    /**
     * Constructs a VictoryPanel with the given score to display game statistics.
     *
     * @param score The final score earned by the player.
     */
    public VictoryPanel(GameState gameState) {
        this.gameState = gameState;
        //this.score = score;
        initComponents();
    }

    /**
     * Initializes the components of the VictoryPanel, including labels to display the victory message and points earned.
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0; // Expand horizontally to fill the available space
        gbc.anchor = GridBagConstraints.CENTER; // Center the components horizontally

        JLabel victoryLabel = new JLabel("Victory!");
        victoryLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel pointsLabel = new JLabel("Points: " + gameState.getScore());
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 25));

        add(victoryLabel, gbc);
        add(pointsLabel, gbc);
    }
}
