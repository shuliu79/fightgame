package com.liushu.game.fight.core.model.buff

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by asus-pc on 2016-10-2.
 */
class BuffFactory {

    static AtomicInteger idSequence = new AtomicInteger(0)

    static Buff createBuff(Class<Buff> buffClass){
        Buff buff = buffClass.newInstance()
        buff.id = idSequence.getAndAdd(1)
        return buff
    }

}
