package it.unibo.graphics.impl;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import java.net.URL;

import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.input.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.GameState;

public class SceneImpl implements Scene {

    private JFrame frame;
    private JPanel panel;
    private GameState gameState;
    private JLayeredPane layeredPane;
    private KeyboardInputController controller;
    private BallGraphicsComponent ballGraphicsComponent;
    private BoardGraphicComponent boardGraphics;

    public SceneImpl(GameState gameState, KeyboardInputController controller, String backgroundSrc) {
        this.gameState = gameState;
        this.controller = controller;
        this.frame = new JFrame("Luxor");
        this.ballGraphicsComponent = new BallGraphicsComponent();
        this.boardGraphics = new BoardGraphicComponent(backgroundSrc);
        frame.setMinimumSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                boardGraphics.getBackgorundImg().getHeight(null)));
        frame.setResizable(false);

        // questo
        this.panel = new ScenePanel();
        this.panel.setPreferredSize(new Dimension(this.boardGraphics.getBackgorundImg().getWidth(null),
                this.boardGraphics.getBackgorundImg().getHeight(null)));
        // Use JLayeredPane to layer the components
        // Use JLayeredPane to layer the components
        this.layeredPane = new JLayeredPane();
        this.layeredPane.setPreferredSize(new Dimension(boardGraphics.getBackgorundImg().getWidth(null),
                boardGraphics.getBackgorundImg().getHeight(null)));

        this.layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        this.layeredPane.setLayer(panel, JLayeredPane.DEFAULT_LAYER);

        // Add the button to the layered pane and set its position
        JButton menuButton = new JButton("Menu");
        menuButton.setPreferredSize(new Dimension(80, 30));
        this.layeredPane.add(menuButton, JLayeredPane.PALETTE_LAYER);
        this.layeredPane.setLayer(menuButton, JLayeredPane.PALETTE_LAYER);
        menuButton.setBounds(boardGraphics.getBackgorundImg().getWidth(null) - 100,
                this.boardGraphics.getBackgorundImg().getHeight(null) - 30, 80, 30);

        // Set the action listener for the button
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGame menuGame = new MenuGame();
                menuGame.setVisible(true); // Make the MenuGame frame visible
                frame.dispose();
            }
        });

        frame.getContentPane().add(layeredPane); // Add the layered pane to the frame

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                // System.exit(-1);
            }

            public void windowClosed(WindowEvent ev) {
                // System.exit(-1);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void render() {
        if (panel.isVisible()) {
            Graphics g = panel.getGraphics();
            if (g != null) {
                try {
                    frame.repaint();
                    var cannon = gameState.getWorld().getCannon();
                    if (cannon != null) {
                        CannonGraphicsComponent cannonGraphicsComponent = new CannonGraphicsComponent();
                        cannonGraphicsComponent.update(cannon, (java.awt.Graphics2D) g);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void renderGameOver() {
        try {
            frame.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void renderMenu() {
        // TODO Auto-generated method stub

        // viene chiamato quando il metodo isGameOver ritorna true
        throw new UnsupportedOperationException("Unimplemented method 'renderMenu'");
    }

    public class ScenePanel extends JPanel implements KeyListener {

        public ScenePanel() {
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

        public void paint(Graphics g) { // qui dove disegna le cose
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

            // caricai il cannone
            if (cannon != null) {
                Image image = null;
                try {
                    URL imageUrl = getClass().getResource("/images/cannone.png");
                    if (imageUrl != null) {
                        image = ImageIO.read(imageUrl);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (image != null) {
                    g2.drawImage(image, (int) cannon.getCurrentPos().x, (int) cannon.getCurrentPos().y, null);
                }
            }

            final var entities = gameState.getWorld().getQueue();
            // carico le palline
            for (int i = 0; i < entities.size(); i++) {
                Ball ball = entities.get(i);
                ballGraphicsComponent.update(ball, g2);
            }
            var cannonBalls = gameState.getWorld().getCannon().getFiredBalls();
            cannonBalls.add(gameState.getWorld().getCannon().getStationaryBall());

            for (int i = 0; i < cannonBalls.size(); i++) {
                Ball ball = cannonBalls.get(i);
                ballGraphicsComponent.update(ball, g2);
            }
        }

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

        @Override
        public void keyTyped(KeyEvent e) {
        }

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