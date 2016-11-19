package com.liushu.game.fight.core.model.buff

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.listener.base.BaseListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-2.
 */
abstract class Buff<T extends Unit> {

    String id
    Unit holder // 不知道有没有用
    List<BaseListener> listeners

    def afterAdd(T unit) {}
    def afterRemove(T unit) {}

    def addListener(Class<BaseListener> clazz){
        addListener(ListenerFactory.createListener(clazz))
    }

    def addListener(BaseListener listener){
        listener.holder = holder
        if (listeners==null){
            listeners = new ArrayList<>()
        }
        listeners.add(listener)
    }

    def getListener(Class<BaseListener> listener){
        if (listeners == null){
            return null
        }
        listeners.each {
            if (it.class.equals(listener)){
                return it
            }
        }
        return null
    }

}
