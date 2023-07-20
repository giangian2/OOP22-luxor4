package it.unibo.enums;

public enum Levels {
    L1("Livello 1"),
    L2("Livello 2");

    private final String levelName;

    // Costruttore privato per l'enum
    private Levels(String levelName) {
        this.levelName = levelName;
    }

    // Metodo per ottenere il nome del livello
    public String getLevelName() {
        return levelName;
    }
}
