package com.liushu.game.fight.core.model.buff

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.factory.EventPublisherFactory

/**
 * Created by asus-pc on 2016-10-2.
 */
class BuffPoolFactory {

    EventPublisherFactory factory

    BuffPool createBuffPool(Unit holder){
        def pool = new SimpleBuffPool(holder)
        pool.publisher = factory.createPublisher(holder)
        pool.buffMap = new HashMap<>()
        return pool
    }

}
