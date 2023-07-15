package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;

public class PauseGameEvent implements WorldEvent{

    private boolean pause;

    public PauseGameEvent(){
        pause=true;
    }

    public boolean isPaused(){
        return pause;
    }
}
