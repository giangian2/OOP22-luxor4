package it.unibo.model;

import it.unibo.input.*;
import it.unibo.utils.*;
 

public class GameObject{

    private P2d pos; 
    private String color;
    private V2d vel; 
    private InputComponent input;


    protected GameObject(P2d pos, String color, V2d vel, InputComponent input){
        this.pos= pos;
        this.color= color;
        this.vel= vel;
        this.input = input;
    }

    public void setPos(P2d pos){
		this.pos = pos;
	}

    public void setColor(String color){
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

}



	
	
	
	

	

	
	
	
	


