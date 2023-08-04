package it.unibo.graphics.impl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.model.GameState;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * A custom JPanel representing the game over screen with game statistics.
 */

public class GameOverPanel extends JPanel {
    private transient GameState gameState;
    private static final int LABEL_FONT_SIZE = 25;

    /**
     * Constructs a GameOverPanel with the given game state to display game
     * statistics.
     *
     * @param gameState The current GameState containing game information.
     */

    public GameOverPanel(final GameState gameState) {
        this.gameState = gameState;
        initComponents();
    }

    /**
     * Initializes the components of the GameOverPanel, including labels to display
     * game over message and points earned.
     */

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0; // Expand horizontally to fill the available space
        gbc.anchor = GridBagConstraints.CENTER; // Center the components horizontally

        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, LABEL_FONT_SIZE));

        JLabel pointsLabel = new JLabel("Points: " + gameState.getScore());
        pointsLabel.setFont(new Font("Arial", Font.BOLD, LABEL_FONT_SIZE));

        add(gameOverLabel, gbc);
        add(pointsLabel, gbc);
    }
}
