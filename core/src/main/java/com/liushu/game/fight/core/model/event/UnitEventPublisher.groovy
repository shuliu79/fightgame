package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.listener.base.UnitMatch

/**
 * Created by asus-pc on 2016-9-29.
 */
class UnitEventPublisher extends BaseEventPublisher{

    Unit unit

    @Override
    boolean publishEvent(Event event) {
        def listeners = listenerMap.get(event.class,[:]).values().sort{-it.priority}
        listeners.each {//必须匹配英雄才触发，匹配的英雄可以是技能释放方，也可能是技能作用方
            def listener = it
//            if (listener instanceof UnitMatch){
//                if (event instanceof UnitRelation){
//                    if(!listener.canHandle(event as UnitRelation,unit)){
//                        return false
//                    }
//                }else{
//                    return false
//                }
//            }
            if(listener.canHandle(event)) {
                try {
                    listener.handle(event)
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
        }
    }
}
