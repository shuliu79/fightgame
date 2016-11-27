package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-9-30.
 */
class EventExecutor {

    def executeEvent(BaseEvent event, EventPublisher... publishers) {
        event.status = StaticValues.BEFORE_ACTION
        publishers.each {
            it.publishEvent(event)
        }
        event.execute()
        event.status = StaticValues.AFTER_ACTION
        publishers.each {
            it.publishEvent(event)
        }
    }

}
