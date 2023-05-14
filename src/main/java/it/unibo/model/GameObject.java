package it.unibo.model;

import main.java.it.unibo.common.*;
import main.java.it.unibo.input.*;
import main.java.it.unibo.graphics.*;
 

public class GameObjects{

    private P2d pos; 
    private string color;
    private V2d vel; 
    private InputComponent input;
    private GraphicsComponent graph; 

    protected GameObjects(P2d pos, string color, V2d vel, InputComponent input, GraphicsComponent graph){
        this.posball= posball;
        this.color= color;
        this.vel= vel;
        this.input = input;
        this.graph = graph;
    }

    public void setPos(P2d pos){
		this.pos = pos;
	}

    public void setColor(string color){
        this.color= color;
    }

    public void setVel(V2d vel){
		this.vel = vel;
	}

    public P2d getCurrentPos(){
		return pos;
	}
	
	public V2d getCurrentVel(){
		return vel;
	}

    public void updateInput(InputController c){
		input.update(this, c);
	}

    public void updateGraphics(Graphics g){
		graph.update(this, g);
	}


}



	
	
	
	

	

	
	
	
	


