package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.buff.BuffPool
import com.liushu.game.fight.core.model.event.BaseEvent

/**
 * Created by asus-pc on 2016-11-22.
 */
class TowerImpl extends Tower implements EventPublisher{

    //一个独立的eventPublisher，一般不会使用,而是使用buffpool
    EventPublisher eventPublisher

    @Override
    boolean registerListener(Listener listener) {
        eventPublisher.registerListener(listener)
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
