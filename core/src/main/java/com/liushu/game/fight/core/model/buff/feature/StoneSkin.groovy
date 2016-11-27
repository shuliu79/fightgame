package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.buff.Buff

/**
 * Created by asus-pc on 2016-10-5.
 */
class StoneSkin extends Buff implements Feature{

    def armorValue = 4

    @Override
    def afterAdd() {
        holder.armor.enhance(armorValue)
    }

    @Override
    def afterRemove() {
        holder.armor.weaken(armorValue)
    }
}
