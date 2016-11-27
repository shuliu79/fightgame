package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.listener.base.BaseListener
import com.liushu.game.fight.core.model.listener.base.UnitMatch

/**
 * Created by asus-pc on 2016-9-29.
 */
class SimpleEventPublisher extends BaseEventPublisher{

    def holder

    SimpleEventPublisher(holder) {
        this.holder = holder
    }

    @Override
    boolean publishEvent(Event event) {
        def listeners = listenerMap.get(event.class,[:]).values().sort{-it.priority}
        listeners.each {//必须匹配英雄才触发，匹配的英雄可以是技能释放方，也可能是技能作用方
            def listener = it
            if(listener.canHandle(event)) {
                try {
                    listener.handle(event)
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Override
    boolean registerListener(Listener listener) {
        (listener as BaseListener).holder = holder
        return super.registerListener(listener)
    }
}
