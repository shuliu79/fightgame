package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.SimpleEventPublisher

/**
 * Created by asus-pc on 2016-9-29.
 */
class EventPublisherFactory {

    def EventPublisher createPublisher(Object holder){
        def publisher = new SimpleEventPublisher(holder)
        return publisher
    }

}
