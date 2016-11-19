package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.listener.base.BaseListener

/**
 * Created by asus-pc on 2016-9-22.
 * 每个hero有一个eventPublisher，负责记录这个英雄目前有哪些状态
 */
class BaseEventPublisher implements EventPublisher{

    Map<Class,Map<String,BaseListener>> listenerMap = new HashMap<>();

    @Override
    boolean registerListener(Listener listener) {
        if (!listener instanceof BaseListener){
            throw new RuntimeException("unsupport listener")
        }else{
            listener = listener as BaseListener
            def listeners = listenerMap.get(listener.eventClazz,[:])
            listeners.put(listener.id,listener)
        }
    }

    @Override
    boolean publishEvent(Event event) {
        def listeners = listenerMap.get(event.class,[:])
        listeners.each {
            if (it.value.canHandle(event)) {
                try {
                    it.value.handle(event)
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
        }
    }

    boolean removeListener(BaseListener listener){
        def listeners = listenerMap.get(listener.eventClazz,[:])
        if (listeners.containsKey(listener.id)){
            listeners.remove(listener.id)
            return true
        }else{
            return false
        }
    }

    boolean removeListener(String id,Class clazz){
        if (clazz instanceof BaseListener){
            def method = clazz.getMethod("")
            def listeners = listenerMap.get("getEventClazz")
            if (listeners.containsKey(id)){
                listeners.remove(id)
                return true
            }else{
                return false
            }
        }else{
            return false
        }
    }

    @Deprecated
    @Override
    boolean removeListener(Class clazz) {
        if (clazz instanceof BaseListener){
            def method = clazz.getMethod("")
            def listeners = listenerMap.get("getEventClazz")
            def result = false
            listeners.each {
                if (it.value.class.equals(clazz)){
                    listeners.remove(it.key)
                    result = true
                }
            }
            return result
        }else{
            return false
        }
    }

}
