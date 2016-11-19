package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.buff.Buff

/**
 * Created by asus-pc on 2016-10-6.
 */
class MagicPowerEnhance extends Buff<Hero> implements Feature{

    static final magicPowerValue = 2

    Hero hero

    @Override
    def afterAdd(Hero hero) {
        hero.magicPower.enhance(magicPowerValue)
    }

    @Override
    def afterRemove(Hero hero) {
        hero.magicPower.weaken(magicPowerValue)
    }

}
