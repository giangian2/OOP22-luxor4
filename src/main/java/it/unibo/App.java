package it.unibo;

import it.unibo.graphics.impl.MenuGame;

/**
 * The main class for starting the application.
 */
public final class App {

     /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private App() {
        // Private constructor to prevent instantiation
    }

    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(final String[] args) {
      /**
       * Create and show the main menu frame.
       */
        MenuGame mainFrame = new MenuGame();
        mainFrame.setVisible(true);
    }
}
