package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.buff.Buff

/**
 * Created by asus-pc on 2016-10-6.
 */
class MagicPowerEnhance extends Buff<Hero> implements Feature{

    def magicPowerValue = 2

    @Override
    def afterAdd() {
        holder.magicPower.enhance(magicPowerValue)
    }

    @Override
    def afterRemove() {
        holder.magicPower.weaken(magicPowerValue)
    }

}
