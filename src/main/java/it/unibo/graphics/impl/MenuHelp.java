package it.unibo.graphics.impl;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.BorderLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MenuHelp extends JFrame{
    public MenuHelp(MenuGame menuGame) {

        setTitle("Luxor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /**
        * Principal panel.
        * */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        /**
        * Text area.
        * */
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        /**
        * Loads file text.
        * */
        try {
            String filePath = "src/main/resources/help/help.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            textArea.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
        * Button Back.
        * */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);

        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentY(Component.CENTER_ALIGNMENT);        
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Nasconde la finestra di help
                menuGame.setVisible(true); // Mostra la finestra del menu
            }
        });

        buttonPanel.add(back);
    }
}
