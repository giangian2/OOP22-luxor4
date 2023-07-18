package it.unibo.model;

import it.unibo.utils.Path;
import it.unibo.utils.P2d;
import it.unibo.core.impl.GameObjectsFactory;

import java.util.ArrayList;
import java.util.List;

//il levelManager class carica un nuovo livello nel gioco, fa 
//l'update del cannone e delle palline, nuova schermata
//-->avanzamento dei livelli

public class LevelManager {
    private List<GameObject> levels;
    private int currentLevelIndex;
    GameState gameState; 
    private int points; 

    //si parte dal liv1
    public LevelManager() {
        levels = new ArrayList<>(null);
        currentLevelIndex=1;
        int points = gameState.getScore(); 
        points = 0;
    }

    //se non hai finito i livelli carica il livello succ
    public void goToNextLevel(){
        if (currentLevelIndex <= levels.size()) {
            currentLevelIndex++;
            startLevel();
            points = 0;
        }
    }

    //carico tutto nel liv
    public void startLevel(){
        getMap();
        getSceneEntities();
    }
    
    //caricherà l'img di sfondo del liv succ
    public void getMap(){

    }

    //setta percorso palline e cannone in pos iniziale
    //con nuovo percorso
    public void getSceneEntities(){
        Path.PathBuilder pathBuilder = new Path.PathBuilder();
        Path path = pathBuilder.build();
        P2d cannonPos = new P2d(0,0);
        Cannon cannon = GameObjectsFactory.getInstance().createCannon(cannonPos);
    }
}
