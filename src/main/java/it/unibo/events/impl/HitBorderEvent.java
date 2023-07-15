package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.utils.P2d;

public class HitBorderEvent implements WorldEvent {
    private P2d where;

    public HitBorderEvent(P2d where) {
        this.where = where;
    }

    public P2d getWhere() {
        return where;
    }
}