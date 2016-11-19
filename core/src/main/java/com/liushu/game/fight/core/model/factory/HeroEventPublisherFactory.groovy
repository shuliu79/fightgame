package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.UnitEventPublisher

/**
 * Created by asus-pc on 2016-9-29.
 */
class HeroEventPublisherFactory {

    def static createUnitEventPublisher(Unit unit){
        def publisher = new UnitEventPublisher()
        publisher.unit = unit
        return publisher
    }

}
