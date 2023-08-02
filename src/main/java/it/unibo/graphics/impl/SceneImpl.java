package it.unibo.graphics.impl;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.input.impl.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.GameState;

/**
 * An implementation of the Scene interface responsible for rendering the game graphics.
 */
public class SceneImpl implements Scene {

    private JFrame frame;
    private JPanel panel;
    private GameState gameState;
    private JLayeredPane layeredPane;
    private KeyboardInputController controller;
    private BoardGraphicComponent boardGraphics;
    private CannonGraphicsComponent cannonGraphicsComponent;
    private JLabel pointsLabel;
    private VictoryPanel victoryPanel;

    /**
     * Constructs a SceneImpl with the specified GameState, KeyboardInputController, background image source, and cannon image source.
     *
     * @param gameState     The current GameState of the game.
     * @param controller    The KeyboardInputController for handling user input.
     * @param backgroundSrc The path to the background image source.
     * @param cannonSrc     The path to the cannon image source.
     */
    public SceneImpl(GameState gameState, KeyboardInputController controller, String backgroundSrc) {
        this.gameState = gameState;
        this.controller = controller;
        this.frame = new JFrame("Luxor");
        this.boardGraphics = new BoardGraphicComponent(backgroundSrc);
        frame.setMinimumSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
        boardGraphics.getBackgorundImg().getHeight(null)));
        frame.setResizable(false);
        this.cannonGraphicsComponent = new CannonGraphicsComponent();

        
        this.panel = new GamePanel();
        this.panel.setPreferredSize(new Dimension(this.boardGraphics.getBackgorundImg().getWidth(null),
        this.boardGraphics.getBackgorundImg().getHeight(null)));
        
        this.layeredPane = new JLayeredPane();
        this.layeredPane.setPreferredSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
        boardGraphics.getBackgorundImg().getHeight(null)));

        this.layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        this.layeredPane.setLayer(panel, JLayeredPane.DEFAULT_LAYER);

        
        JButton menuButton = new JButton("Menu");
        menuButton.setPreferredSize(new Dimension(80, 30));
        this.layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        this.layeredPane.setLayer(menuButton, JLayeredPane.PALETTE_LAYER);
        menuButton.setBounds(boardGraphics.getBackgorundImg().getWidth(null) - 100,
        this.boardGraphics.getBackgorundImg().getHeight(null) - 30, 80, 30);

        

        pointsLabel = new JLabel("Punti: " + gameState.getScore());
        this.layeredPane.add(pointsLabel, JLayeredPane.PALETTE_LAYER);
        this.layeredPane.setLayer(pointsLabel, JLayeredPane.PALETTE_LAYER);

        
        int labelX = 10; 
        int labelY = this.boardGraphics.getBackgorundImg().getHeight(null) - 30;
        int labelWidth = 100; 
        int labelHeight = 20; 
        pointsLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        pointsLabel.setForeground(Color.WHITE); 

        
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.getWorld().stopMusic();
                MenuGame menuGame = new MenuGame();
                menuGame.setVisible(true); 
                frame.dispose();
            }
        });

        frame.getContentPane().add(layeredPane); 

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                
            }

            public void windowClosed(WindowEvent ev) {
                
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Renders the game graphics on the panel.
     */
    @Override
    public void render() {
        if (panel.isVisible()) {
            Graphics g = panel.getGraphics();
            if (g != null) {
                try {
                    frame.repaint();
                    
                    // Update pointsLabel with the current score
                    pointsLabel.setText("Points: " + gameState.getScore());
    
                    // Check for the victory condition and display the victory panel
                    if (gameState.isWin()) {
                        renderWin();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Renders the game over screen and transitions to the game over menu.
     */
    @Override
    public void renderGameOver() {
        try {
            gameState.getWorld().stopMusic();
            MenuGame menuGame = new MenuGame();
            menuGame.showGameOver(gameState);
            menuGame.setVisible(true);

            frame.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    /**
     * Renders the win screen and transitions to the win menu.
     */
    @Override
    public void renderWin() {
        try {
            gameState.getWorld().stopMusic();
            MenuGame menuGame = new MenuGame();
            menuGame.showWin(gameState);
            menuGame.setVisible(true);

            frame.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        
        }
    }

    /**
     * Renders the game menu. (Note: This method is not yet implemented.)
     */
    @Override
    public void renderMenu() {
        // TODO Auto-generated method stub

        throw new UnsupportedOperationException("Unimplemented method 'renderMenu'");
    }

    public class GamePanel extends JPanel implements KeyListener {
        /**
        * An inner class representing the panel where the game graphics are drawn.
        */
        public GamePanel() {
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();

            Dimension size = new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                    boardGraphics.getBackgorundImg().getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(new GridLayout());

        }

        /**
         * Paints the graphics on the panel.
         *
         * @param g The Graphics object used for painting.
         */
        public void paint(Graphics g) { 
            if (g == null) {
                return;
            }
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());

            g2.setColor(Color.WHITE);

            boardGraphics.update(null, g2);

            var cannon = gameState.getWorld().getCannon();
            cannon.updateGraphics(g2);

            final var entities = gameState.getWorld().getQueue();
            // carico le palline
            for (int i = 0; i < entities.size(); i++) {
                Ball ball = entities.get(i);
                ball.updateGraphics(g2);
            }
            var cannonBalls = gameState.getWorld().getCannon().getFiredBalls();
            cannonBalls.add(gameState.getWorld().getCannon().getStationaryBall());

            for (int i = 0; i < cannonBalls.size(); i++) {
                Ball ball = cannonBalls.get(i);
                ball.updateGraphics(g2);
            }

        }

        /**
         * Handles the key presses from the user and notifies the KeyboardInputController accordingly.
         *
         * @param e The KeyEvent containing the key press information.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 39) {
                controller.notifyMoveRight();
            } else if (e.getKeyCode() == 37) {
                controller.notifyMoveLeft();
            } else if (e.getKeyCode() == 80) {
                gameState.getWorld().notifyWorldEvent(new PauseGameEvent());
            } else if (e.getKeyCode() == 32) {
                controller.notifyShoot();
                System.out.println("Shooting");
            }
        }

        /**
         * Unused keyTyped event.
         *
         * @param e The KeyEvent containing the key typed information.
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * Handles the key releases from the user and notifies the KeyboardInputController accordingly.
         *
         * @param e The KeyEvent containing the key release information.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 39) {
                controller.notifyNoMoreMoveRight();
            } else if (e.getKeyCode() == 37) {
                controller.notifyNoMoreMoveLeft();
            }
        }

    }

}