package com.liushu.game.fight.core.model.buff

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.factory.HeroEventPublisherFactory

/**
 * Created by asus-pc on 2016-10-2.
 */
class BuffPoolFactory {

    static BuffPool createBuffPool(Unit unit){
        def pool = new SimpleBuffPool()
        pool.publisher = HeroEventPublisherFactory.createUnitEventPublisher(unit)
        pool.unit = unit
        pool.buffMap = new HashMap<>()
        return pool
    }

}
