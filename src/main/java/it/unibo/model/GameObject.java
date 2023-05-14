package it.unibo.model;

<<<<<<< HEAD
import it.unibo.utils.*;
import it.unibo.input.*;

public class GameObjects {

    private P2d pos;
    private String color;
    private V2d vel;
    private InputComponent input;
    private GraphicsComponent graph;

    protected GameObjects(P2d pos, String color, V2d vel, InputComponent input, GraphicsComponent graph) {
        this.posball = posball;
        this.color = color;
        this.vel = vel;
=======
import it.unibo.enums.BallColor;
import it.unibo.input.*;
import it.unibo.utils.*;
 

public class GameObject{

    private P2d pos; 
    private BallColor color;
    private V2d vel; 
    private InputComponent input;


    protected GameObject(P2d pos, BallColor color, V2d vel, InputComponent input){
        this.pos= pos;
        this.color= color;
        this.vel= vel;
>>>>>>> 415d1de556366426b1ba03b199c62858d7a1867e
        this.input = input;
    }

    public void setPos(P2d pos) {
        this.pos = pos;
    }

<<<<<<< HEAD
    public void setColor(string color) {
        this.color = color;
=======
    public void setColor(BallColor color){
        this.color= color;
>>>>>>> 415d1de556366426b1ba03b199c62858d7a1867e
    }

    public void setVel(V2d vel) {
        this.vel = vel;
    }

    public P2d getCurrentPos() {
        return pos;
    }

<<<<<<< HEAD
    public V2d getCurrentVel() {
        return vel;
    }

    public void updateInput(InputController c) {
        input.update(this, c);
    }

    public void updateGraphics(Graphics g) {
        graph.update(this, g);
    }
=======
  //   public void updateInput(InputController c){
	// 	input.update(this, c);
	// }
>>>>>>> 415d1de556366426b1ba03b199c62858d7a1867e

}
