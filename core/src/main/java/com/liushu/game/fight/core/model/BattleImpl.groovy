package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.EventExecutor
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-22.
 */
class BattleImpl extends Battle implements EventPublisher {

    //环境监听
    EventPublisher publisher
    EventExecutor eventExecutor

    @Override
    def executeEvent(BaseEvent event, EventPublisher sourceTeam, EventPublisher targetTeam) {
        eventExecutor.executeEvent(event,publisher,sourceTeam,targetTeam)
    }

    @Override
    boolean registerListener(Listener listener) {
        return publisher.registerListener(listener)
    }

    @Override
    boolean publishEvent(Event event) {
        return publisher.publishEvent(event)
    }

    @Override
    boolean removeListener(Class clazz) {
        return publisher.removeListener(clazz)
    }
}
