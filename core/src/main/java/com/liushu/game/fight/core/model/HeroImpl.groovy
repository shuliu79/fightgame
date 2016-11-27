package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.buff.BuffPool

/**
 * Created by asus-pc on 2016-11-22.
 */
class HeroImpl extends Hero implements EventPublisher{

    EventPublisher eventPublisher


    @Override
    boolean registerListener(Listener listener) {
        return eventPublisher.registerListener(listener)
    }

    @Override
    boolean publishEvent(Event event) {
        eventPublisher.publishEvent(event)
        buffPool.publishEvent(event)
    }

    @Override
    boolean removeListener(Class clazz) {
        eventPublisher.removeListener(clazz)
    }
}
