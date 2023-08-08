package it.unibo;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import it.unibo.graphics.impl.MenuGame;

/**
 * The main class for starting the application.
 */
public final class App {

    static {
        final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public String formatMessage(final LogRecord record) {
                return String.format(FORMAT,
                        new Date(record.getMillis()),
                        record.getLevel().getLocalizedName(),
                        record.getMessage());
            }
        });
        Logger.getGlobal().setUseParentHandlers(false);
        Logger.getGlobal().addHandler(handler);
    }

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private App() {
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
        final MenuGame mainFrame = new MenuGame();
        mainFrame.setVisible(true);
    }

}
