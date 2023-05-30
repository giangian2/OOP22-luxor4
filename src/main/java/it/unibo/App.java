package it.unibo;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.core.api.GameEngine;

import it.unibo.graphics.impl.MenuGame;

public class App {
    public static void main(String[] args) {
        GameEngine engine = new GameEngineImpl();
        engine.initGame();
        engine.mainLoop();
        
        MenuGame menuGame = new MenuGame();
        menuGame.setVisible(true);
    }
}
