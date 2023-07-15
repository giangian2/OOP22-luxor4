package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;

public class RestartGameEvent implements WorldEvent{

    private boolean pause;

    public RestartGameEvent(){
        pause=false;
    }

    public boolean isPaused(){
        return pause;
    }
}

