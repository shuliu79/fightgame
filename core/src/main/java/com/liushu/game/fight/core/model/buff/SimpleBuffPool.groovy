package com.liushu.game.fight.core.model.buff

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.buff.feature.Feature
import com.liushu.game.fight.core.model.event.UnitEventPublisher
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

/**
 * Created by asus-pc on 2016-10-2.
 */
class SimpleBuffPool implements BuffPool{

    Log log = LogFactory.getLog(SimpleBuffPool)
    //1.记录单位拥有的状态，buff等，buff可能对应多个listener
    //2.包含了多个listener，可以发布事件
    //3.提供查询buff等接口
    //4.随着回合迭代，对buff进行管理

    Unit unit
    UnitEventPublisher publisher
    Map<String,BuffHolder> buffMap

    @Override
    def addBuff(int time, Buff buff) {
        log.debug("add buff ${buff.class.simpleName},${time}")
        def buffHolder = new BuffHolder(buff,time)
        buffMap.put(buff.id,buffHolder)
        if (buff.listeners!=null) {
            buff.listeners.each {
                publisher.registerListener(it)
            }
        }
        buff.afterAdd(unit)
    }

    @Override
    def removeBuff(Buff buff) {
        log.debug("remove buff ${buff.class.simpleName}")
        if (buffMap.containsKey(buff.id)){
            if (buff.listeners!=null) {
                buff.listeners.each {
                    publisher.removeListener(it)
                }
            }
            buffMap.remove(buff.id)
            buff.afterRemove(unit)
        }else{
            log.warn("buff not found:${buff.class.simpleName} ${buff.id}")
        }
    }

    @Override
    boolean hasBuff(Class clazz) {
        buffMap.values().each {
            if (it.buff.class.equals(clazz)){
                return true
            }
        }
        return false
    }

    @Override
    def nextRound() {
        buffMap.values().toList().each {
            if(!it.buff instanceof Feature) {
                it.time -= 1
                if (it.time == 0) {
                    removeBuff(it.buff)
                }
            }
        }
    }

    @Override
    def publishEvent(Event event) {
        publisher.publishEvent(event)
    }
}
