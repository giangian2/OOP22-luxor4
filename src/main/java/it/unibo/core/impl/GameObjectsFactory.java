package it.unibo.core.impl;

import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.utils.Path;

//guarda anche se nel game object hanno aggiunto qualcosa
import it.unibo.enums.BallColor;
import it.unibo.enums.Direction;
import it.unibo.events.*;
import it.unibo.graphics.*;
import it.unibo.input.*;
import it.unibo.model.*;
import it.unibo.utils.*;
import it.unibo.physics.*;




public class GameObjectsFactory {

    static private GameObjectsFactory instance;

    static public GameObjectsFactory getInstance(){
        if (instance == null)
        {
            instance = new GameObjectsFactory();
        }
        return instance;
    }

    //note della fede
    //GameObject.Type.BALL??
    //metodi del cannon

    public GameObject createBall(P2d pos, V2d vel, ColorBall ball){ 
		return new GameObject(GameObject.Type.BALL, pos, new Ball(new P2d(pos.x, pos.y), new V2d (vel.x, vel.y), ball),
				new PlayerInputComponent(), // in input //mi serve l'input della palla? per me no
				new BallGraphicsComponent(), //in graphics
				new BallPhysicsComponent()); //in physics

    }

    public GameObject createCannon(P2d pos){ 
            return new GameObject(GameObject.Type.CANNON, pos,
                    new PlayerInputComponent(), // in input
                    new CannonGraphicsComponent(), //in graphics
                    new CannonPhysicsComponent()); //in physics
    }


}
