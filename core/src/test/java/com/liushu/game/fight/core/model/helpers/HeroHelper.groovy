package com.liushu.game.fight.core.model.helpers

import com.liushu.game.fight.core.model.FactoryHolder
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroType

/**
 * Created by asus-pc on 2016-11-27.
 */
class HeroHelper {

    def heroFactory = FactoryHolder.instance.heroFactory

    Hero createSimpleHero(int id){
        def hero = heroFactory.createHero(id,"hero_${id}",HeroType.BLANK,30,30,30)
        return hero
    }
}
