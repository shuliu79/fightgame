package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener

/**
 * Created by asus-pc on 2016-11-21.
 */
class PlayerImpl extends Player implements EventPublisher{

    EventPublisher publisher

    @Override
    boolean registerListener(Listener listener) {
        return publisher.registerListener(listener)
    }

    @Override
    boolean publishEvent(Event event) {
        publisher.publishEvent(event)
        ((EventPublisher)hero).publishEvent(event)
        creatures.each {
            ((EventPublisher)it).publishEvent(event)
        }
    }

    @Override
    boolean removeListener(Class clazz) {
        return publisher.removeListener(clazz)
    }
}
