package it.unibo.graphics.impl;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.enums.Levels;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuGame extends JFrame {

    private Levels selectedLevel;
    private JPanel mainPanel;

    /**
     * The initial menu of the game.
     * */
    public MenuGame() {

        setTitle("Luxor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        selectedLevel = Levels.L1;

        /**
        * Principal panel.
        * */
        mainPanel = new JPanel(new GridLayout(2, 1));
        add(mainPanel);

        showMainMenu();
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.addActionListener(actionListener);
        return button;
    }
        
    private void showHelpMenu() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add the scrollPane to the mainPanel
            
        // Adding some vertical space between the textArea and the "Back" button
        helpPanel.add(Box.createVerticalStrut(10));

        // Load text from file
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        ClassLoader.getSystemResourceAsStream(
                            "help" + System.getProperty("file.separator") + "help.txt")));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            textArea.setText(content.toString());
        } catch (FileNotFoundException e) {
            // Eccezione lanciata quando il file non è trovato
            e.printStackTrace();
        } catch (IOException e) {
            // Eccezione generale di I/O
            e.printStackTrace();
        }
        
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });

        helpPanel.add(Box.createVerticalGlue());
        helpPanel.add(back);
        mainPanel.add(helpPanel);
    }

    public void showMainMenu() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        JPanel labelPanel = new JPanel(new GridBagLayout());
        mainPanel.add(labelPanel);

        JLabel label = new JLabel("Welcome to the game Luxor!");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        labelPanel.add(label, constraints);

        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);

        JButton help = createButton("Help", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showHelpMenu();
            }
        });
        buttonPanel.add(help);

        JButton startGame = createButton("Start Game", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Thread thread = new Thread() {
                    @Override
                    public void run() {
                        new GameEngineImpl(selectedLevel).initGame();
                    }
                };
                thread.start();
                System.out.println("Game started!");
                dispose();
            }
        });
        buttonPanel.add(startGame);

        JButton levelsButton = createButton("Livelli", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = Levels.values();
                Levels selected = (Levels) JOptionPane.showInputDialog(
                    MenuGame.this, // Frame genitore
                    "Seleziona un livello", // Testo del messaggio
                    "Selezione Livello", // Titolo del dialogo
                    JOptionPane.PLAIN_MESSAGE, // Tipo di messaggio
                    null, // Icona personalizzata (null per l'icona di default)
                    options, // Opzioni da mostrare nell'elenco
                    options[0] // Opzione preselezionata (in questo caso, la prima costante dell'enum)
                );

                if (selected != null) {
                    selectedLevel = selected;
                    System.out.println("Livello selezionato: " + selectedLevel.getLevelName());
                }
            }
        });
        buttonPanel.add(levelsButton);

        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public Levels getSelectedLevel() {
        return selectedLevel;
    }
    
    
}