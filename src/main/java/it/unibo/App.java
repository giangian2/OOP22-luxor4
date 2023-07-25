package it.unibo;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.core.api.GameEngine;

import it.unibo.graphics.impl.MenuGame;

public class App {
  public static void main(String[] args) {
    // GameEngine engine = new GameEngineImpl(null);
    // engine.initGame();
    // engine.mainLoop();
    // MenuGame menuGame = new MenuGame(engine);
    MenuGame mainFrame = new MenuGame();
    // menu game e sceneimpl
    // sceneimpl ha bisogno del primo jframe creato (menu game)
    mainFrame.setVisible(true);

    // Create the MenuGame instance
    // MenuGame menuGame = new MenuGame();

    // Create the GameState and KeyboardInputController instances
    // GameState gameState = new GameState(null);
    // KeyboardInputController controller = new KeyboardInputController();

    // Create the SceneImpl instance and pass the MenuGame instance to it
    // SceneImpl sceneImpl = new SceneImpl(gameState, controller);

  }
}
