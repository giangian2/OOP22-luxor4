package it.unibo.core.impl;

import it.unibo.enums.BallColor;
//guarda anche se nel game object hanno aggiunto qualcosa
import it.unibo.events.api.*;
import it.unibo.events.impl.*;
import it.unibo.graphics.impl.CannonGraphicsComponent;
import it.unibo.input.InputComponent;
import it.unibo.input.PlayerInputComponent;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;
import it.unibo.model.GameObject.Type;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.physics.impl.BallPhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

public class GameObjectsFactory {

    static private GameObjectsFactory instance;

    static public GameObjectsFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectsFactory();
        }
        return instance;
    }
    // note della fede
    // GameObject.Type.BALL??
    // metodi del cannon

    public Ball createBall(P2d pos, V2d vel, BallColor color) {
        return new Ball(Type.BALL, pos, color, new V2d(pos, pos),
                new PlayerInputComponent(),
                new CircleBoundingBox(), // in input //mi serve l'input della palla? per
                new BallPhysicsComponent()); // in physics

    }
     
     
    //public GameObject createCannon(P2d pos){
        //return new GameObject(GameObject.Type.CANNON, pos,
        //new PlayerInputComponent(), // in input
        //new CannonGraphicsComponent(), //in graphics
       // new CannonPhysicsComponent()); //in physics
    //}
      

}
