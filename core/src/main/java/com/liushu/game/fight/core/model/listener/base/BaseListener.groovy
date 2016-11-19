package com.liushu.game.fight.core.model.listener.base

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-9-22.
 */
abstract class BaseListener<T extends BaseEvent> implements Listener {

    String id
    int priority = StaticValues.NORMAL
    Unit holder

    protected void doBeforeExecute(T event) {
    }

    protected void doAfterExecute(T event) {
    }

//    void doBeforeHandle(T event){
//    }
//
//    void afterHandle(T event){
//    }

    protected boolean handleIfDead(){
        return true
    }

    @Override
    boolean canHandle(Event event) {
        //如果属于死了不执行的listener，则检查是否死亡
        if (holder!=null && !handleIfDead() && holder.death){
            return false
        }
        event = event as T
        if (event.class.equals(getEventClazz())) {
            return canHandle_(event)
        } else {
            return false
        }
    }

    @Override
    void handle(Event event) {
        event = event as T
        if (event.status.equals(StaticValues.BEFORE_ACTION)) {
            doBeforeExecute(event)
        }else{
            doAfterExecute(event)
        }
    }

    protected boolean canHandle_(T event) {
        return true
    }

    @Override
    void destroy() {
    }

    protected abstract Class getEventClazz()

}
