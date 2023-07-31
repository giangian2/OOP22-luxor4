package it.unibo.graphics.impl;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private MenuGame menuGame;

    public GameOverPanel(MenuGame menuGame) {
        this.menuGame = menuGame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = new JLabel("Gioco finito!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(gameOverLabel);

        JButton playAgainButton = createButton("Gioca di nuovo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuGame.showMainMenu();
            }
        });
        add(playAgainButton);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.addActionListener(actionListener);
        return button;
    }
}
