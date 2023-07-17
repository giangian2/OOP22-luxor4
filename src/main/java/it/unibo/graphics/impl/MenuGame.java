package it.unibo.graphics.impl;
import it.unibo.core.impl.GameEngineImpl;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ActionListener;

public class MenuGame extends JFrame {

    /**
     * The initial menu of the game.
     * */
    public MenuGame() {

        setTitle("Luxor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        /**
        * Principal panel.
        * */
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        add(mainPanel);


        /**
        * Label.
        * */
        JPanel labelPanel = new JPanel(new GridBagLayout());
        mainPanel.add(labelPanel);

        JLabel label = new JLabel("Welcome to the game Luxor!");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        labelPanel.add(label, constraints);
        

        /**
        * Button Help.
        * */
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);

        JButton help = new JButton("Help");
        help.setFont(new Font("Arial", Font.PLAIN, 16));
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Nasconde la finestra del menu
                MenuHelp MenuHelp = new MenuHelp(MenuGame.this); // Passa il riferimento alla finestra del menu alla finestra di help
                MenuHelp.setVisible(true); // Mostra la finestra di help
            }
        });

        buttonPanel.add(help);


        /**
        * Button start.
        * */
        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Arial", Font.PLAIN, 16));

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                final Thread thread = new Thread() {
                    @Override
                    public void run() {
                        new GameEngineImpl().initGame();
                    }
                };
                thread.start();

                System.out.println("Game started!");
                dispose();
            }
        });

        buttonPanel.add(startGame);

        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
