package it.unibo;

import it.unibo.graphics.impl.MenuGame;

/**
 * The main class for starting the application.
 */
public class App {

    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
      /**
       * Create and show the main menu frame.
       */
        MenuGame mainFrame = new MenuGame();
        mainFrame.setVisible(true);
    }
}
