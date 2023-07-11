package it.unibo.graphics.impl;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import java.net.URL;

import it.unibo.graphics.api.Scene;
import it.unibo.input.InputComponent;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.World;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.impl.CannonPhysicsComponent;
import it.unibo.input.PlayerInputComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.Path;
import it.unibo.utils.V2d;

public class SceneImpl implements Scene {

    private JFrame frame;
    private JPanel panel;
    private World w;
    private Cannon cannon;

    public SceneImpl(World w) {
        this.w = w;
        this.frame = new JFrame("Roll A Ball");

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
        this.panel = new ScenePanel(image);
        frame.getContentPane().add(panel);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(-1);
            }

            public void windowClosed(WindowEvent ev) {
                System.exit(-1);
            }
        });
        frame.pack();
        frame.setVisible(true);

        P2d cannonPosition = new P2d(10, 10);
        V2d cannonVelocity = new V2d(5, 0);
        InputComponent cannonInput = new PlayerInputComponent();
        CannonPhysicsComponent cannonPhysics = new CannonPhysicsComponent();

        Cannon cannon = new Cannon(cannonPosition, cannonVelocity, cannonInput, null, cannonPhysics);
        setCannon(cannon);
    }  

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderGameOver'");
    }

    @Override
    public void renderMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderMenu'");
    }

    public class ScenePanel extends JPanel implements KeyListener {

        private Image img;
        private Cannon cannon;


        public ScenePanel(Image img) {
            this.img = img;
            if (img != null) {
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
            } else {
                throw new IllegalArgumentException("Invalid image provided");
            }
        }

        public void setCannon(Cannon cannon) {
            this.cannon = cannon;
        }

        public void paint(Graphics g) {
            System.out.println("rendering");
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());

            g2.setColor(Color.WHITE);
            g2.drawImage(img, 0, 0, null);

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


            

            try {
                final var image = ImageIO.read(ClassLoader.getSystemResource("images/blue_ball2.png"));
                var entities = w.getQueue();

                entities.forEach(ball -> {
                    g2.drawImage(image, (int) ball.getCurrentPos().x, (int) ball.getCurrentPos().y, null);
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
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