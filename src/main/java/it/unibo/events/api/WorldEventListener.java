package it.unibo.events.api;

/**
 * 
 * Interface used to model the World-related event listener
 */
public interface WorldEventListener {
    /**
     * Method used to notify a World-related event to the listener
     */
    void notifyEvent(WorldEvent e);
}
