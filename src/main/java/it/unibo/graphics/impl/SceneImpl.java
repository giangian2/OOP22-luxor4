package it.unibo.graphics.impl;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.input.impl.KeyboardInputController;
import it.unibo.model.api.GameState;
import it.unibo.model.impl.Ball;

/**
 * A class representing the game scene.
 */
public class SceneImpl implements Scene {

    private final JFrame frame;
    private final JPanel panel;
    private final GameState gameState;
    private final KeyboardInputController controller;
    private final BoardGraphicComponent boardGraphics;
    private final JLabel pointsLabel;
    private static final int MENU_BUTTON_WIDTH = 80;
    private static final int MENU_BUTTON_HEIGHT = 30;
    private static final int LABEL_HEIGHT = 20;
    private static final int LABEL_X = 10;
    private static final int LABEL_Y = 580;

    /**
     * Constructs a SceneImpl with the specified GameState, KeyboardInputController,
     * background image source, and cannon image source.
     *
     * @param gameState     The current GameState of the game.
     * @param controller    The KeyboardInputController for handling user input.
     * @param backgroundSrc The path to the background image source.
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP" }, justification = "This warning does not represent a security threat"
                    + "beacuse the KeyboardInpuController has to be mutable")

    public SceneImpl(final GameState gameState, final KeyboardInputController controller, final String backgroundSrc) {

        this.gameState = gameState;
        this.controller = controller;
        this.frame = new JFrame("LXR4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.boardGraphics = new BoardGraphicComponent(backgroundSrc);
        frame.setMinimumSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                boardGraphics.getBackgorundImg().getHeight(null)));
        frame.setResizable(false);

        this.panel = new GamePanel();
        ((GamePanel) this.panel).initKeyListener();
        this.panel.setPreferredSize(new Dimension(this.boardGraphics.getBackgorundImg().getWidth(null),
                this.boardGraphics.getBackgorundImg().getHeight(null)));

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                boardGraphics.getBackgorundImg().getHeight(null)));

        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(panel, JLayeredPane.DEFAULT_LAYER);

        final JButton menuButton = new JButton("Menu");
        menuButton.setPreferredSize(new Dimension(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(menuButton, JLayeredPane.PALETTE_LAYER);
        menuButton.setBounds(boardGraphics.getBackgorundImg().getWidth(null) - MENU_BUTTON_WIDTH,
                this.boardGraphics.getBackgorundImg().getHeight(null) - MENU_BUTTON_HEIGHT, MENU_BUTTON_WIDTH,
                MENU_BUTTON_HEIGHT);

        pointsLabel = new JLabel("Punti: " + gameState.getScore());
        layeredPane.add(pointsLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(pointsLabel, JLayeredPane.PALETTE_LAYER);

        final int labelWidth = 100;
        pointsLabel.setBounds(LABEL_X, LABEL_Y, labelWidth, LABEL_HEIGHT);
        pointsLabel.setForeground(Color.WHITE);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                gameState.getWorld().stopMusic();
                final MenuGame menuGame = new MenuGame();
                menuGame.setVisible(true);
                frame.dispose();
            }
        });

        frame.getContentPane().add(layeredPane);

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent ev) {
            }

            @Override
            public void windowClosed(final WindowEvent ev) {
            }
        });
        frame.pack();
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }

    /**
     * Renders the game graphics on the panel.
     */
    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void render() {
        if (panel.isVisible()) {
            final Graphics g = panel.getGraphics();
            if (g != null) {
                try {
                    frame.repaint();
                    // Update pointsLabel with the current score
                    pointsLabel.setText("Points: " + gameState.getScore());

                    // Check for the victory condition and display the victory panel
                    if (gameState.isWin()) {
                        renderWin();
                    }
                } catch (RuntimeException ex) {
                    Logger.getGlobal().log(Level.WARNING, ex.toString());
                }
            }
        }
    }

    /**
     * Renders the game over screen and transitions to the game over menu.
     */
    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void renderGameOver() {
        try {
            gameState.getWorld().stopMusic();
            final MenuGame menuGame = new MenuGame();
            menuGame.showGameOver(gameState);
            menuGame.setVisible(true);

            frame.dispose();
        } catch (RuntimeException ex) {
            Logger.getGlobal().log(Level.WARNING, ex.toString());
        }

    }

    /**
     * Renders the win screen and transitions to the win menu.
     */
    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void renderWin() {
        try {
            gameState.getWorld().stopMusic();
            final MenuGame menuGame = new MenuGame();
            menuGame.showWin(gameState);
            menuGame.setVisible(true);

            frame.dispose();
        } catch (RuntimeException ex) {
            Logger.getGlobal().log(Level.WARNING, ex.toString());
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

    /**
     * An inner class representing the panel where the game graphics are drawn.
     */
    public class GamePanel extends JPanel implements KeyListener {

        private static final long serialVersionUID = 1L;

        /**
         * An inner class representing the panel where the game graphics are drawn.
         */
        public GamePanel() {
            // this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            final Dimension size = new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                    boardGraphics.getBackgorundImg().getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(new GridLayout());

        }

        /**
         * Initializes the key listener for the panel.
         */
        public void initKeyListener() {
            addKeyListener(this);
        }

        /**
         * Paints the graphics on the panel.
         *
         * @param g The Graphics object used for painting.
         */
        @Override
        public void paint(final Graphics g) {
            if (g == null) {
                return;
            }
            final Graphics2D g2 = (Graphics2D) g.create(); // Create a copy of the graphics object

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());

            g2.setColor(Color.WHITE);

            boardGraphics.update(null, g2);

            final var cannon = gameState.getWorld().getCannon();
            cannon.updateGraphics(g2);

            final var entities = gameState.getWorld().getQueue();

            for (int i = 0; i < entities.size(); i++) {
                final Ball ball = entities.get(i);
                ball.updateGraphics(g2);
            }
            final var cannonBalls = gameState.getWorld().getCannon().getFiredBalls();
            cannonBalls.add(gameState.getWorld().getCannon().getStationaryBall());

            for (int i = 0; i < cannonBalls.size(); i++) {
                final Ball ball = cannonBalls.get(i);
                ball.updateGraphics(g2);
            }

            g2.dispose();
        }

        /**
         * Handles the key presses from the user and notifies the
         * KeyboardInputController accordingly.
         *
         * @param e The KeyEvent containing the key press information.
         */
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controller.notifyMoveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controller.notifyMoveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_P) {
                gameState.getWorld().notifyWorldEvent(new PauseGameEvent());
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                controller.notifyShoot();
            }
        }

        /**
         * Unused keyTyped event.
         *
         * @param e The KeyEvent containing the key typed information.
         */
        @Override
        public void keyTyped(final KeyEvent e) {
        }

        /**
         * Handles the key releases from the user and notifies the
         * KeyboardInputController accordingly.
         *
         * @param e The KeyEvent containing the key release information.
         */
        @Override
        public void keyReleased(final KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controller.notifyNoMoreMoveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controller.notifyNoMoreMoveLeft();
            }
        }
    }
}
