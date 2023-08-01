package it.unibo.enums;

/**
 * An enumeration representing different levels.
 */
public enum Levels {

    /** The first level. */
    L1("Livello 1"),

    /** The second level. */
    L2("Livello 2");

    private final String levelName;

    /**
     * Private constructor for the enum.
     *
     * @param levelName The name of the level.
     */
    Levels(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * Get the name of the level.
     *
     * @return The name of the level.
     */
    public String getLevelName() {
        return levelName;
    }
}
