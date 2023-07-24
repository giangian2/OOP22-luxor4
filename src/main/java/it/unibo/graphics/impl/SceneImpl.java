package it.unibo.graphics.impl;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URL;
import java.util.Random;

import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.input.InputComponent;
import it.unibo.input.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.GameState;
import it.unibo.model.World;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.impl.CannonPhysicsComponent;
import it.unibo.input.PlayerInputComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.Path;
import it.unibo.utils.V2d;
import it.unibo.enums.*;

public class SceneImpl implements Scene {

    private JFrame frame;
    private JPanel panel;
    private GameState gameState;
    private KeyboardInputController controller;

    public SceneImpl(GameState gameState, KeyboardInputController controller) {
        this.gameState = gameState;
        this.controller = controller;
        this.frame = new JFrame("Luxor");
       
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setResizable(false);
        // frame.setUndecorated(true); // Remove title bar
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource("images/background.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //questo
        this.panel = new ScenePanel(image);
        
        frame.getContentPane().add(panel);
               

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(-1);
            }

            public void windowClosed(WindowEvent ev) {
                System.exit(-1);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void render() {
        try {
            frame.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void renderGameOver() {
        try{
        frame.repaint();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void renderMenu() {
        // TODO Auto-generated method stub

        //viene chiamato quando il metodo isGameOver ritorna true
        throw new UnsupportedOperationException("Unimplemented method 'renderMenu'");
    }

    public class ScenePanel extends JPanel implements KeyListener {

        private Image img;

        public ScenePanel(Image img) {
            this.img = img;
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();
            if (img != null) {
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(new GridLayout());
            } else {
                throw new IllegalArgumentException("Invalid image provided");
            }
        }

        public void paint(Graphics g) { // qui dove disegna le cose
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());

            g2.setColor(Color.WHITE);

            g2.drawImage(img, 0, 0, null);

            var cannon = gameState.getWorld().getCannon();
           
            //caricai l cannone
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

            try { // carica le palline

                final var ballBlue = ImageIO.read(ClassLoader.getSystemResource("images/blue_ball2.png"));
                final var ballRed = ImageIO.read(ClassLoader.getSystemResource("images/red_ball2.png"));
                final var ballGreen = ImageIO.read(ClassLoader.getSystemResource("images/green_ball2.png"));
                final var ballYellow = ImageIO.read(ClassLoader.getSystemResource("images/yellow_ball2.png"));

                var entities = gameState.getWorld().getQueue(); // entities sono le palline della coda
                // Ottieni il colore della pallina (supponendo che il modello 'ball' abbia un
                // metodo getColor())

                for (var ball : entities) {
                    if (ball.getColor() == BallColor.BLUE) {
                        g2.drawImage(ballBlue, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.RED) {
                        g2.drawImage(ballRed, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.GREEN) {
                        g2.drawImage(ballGreen, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.YELLOW) {
                        g2.drawImage(ballYellow, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    }
                }

                var cannonBalls = gameState.getWorld().getCannon().getFiredBalls();
                for (var ball : cannonBalls) {
                    if (ball.getColor() == BallColor.BLUE) {
                        g2.drawImage(ballBlue, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.RED) {
                        g2.drawImage(ballRed, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.GREEN) {
                        g2.drawImage(ballGreen, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    } else if (ball.getColor() == BallColor.YELLOW) {
                        g2.drawImage(ballYellow, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

    public class BallComponent extends JComponent {

        private P2d pos;

        public BallComponent(P2d pos) {
            super();
            this.pos = pos;
        }

        public void paint(Graphics g) {
            Image image = null;
            try {
                image = ImageIO.read(ClassLoader.getSystemResource("images/blue_ball.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ImageIcon icon = new ImageIcon(image);
            int x = (int) pos.x;
            int y = (int) pos.y;
            icon.paintIcon(this, g, x, y);
        }

    }

}