package com.liushu.game.fight.core.model.buff

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-10-2.
 */
class DefendStatus extends Buff{

    final static defendValue = 10

    @Override
    def afterAdd(Unit unit) {
        unit.armor.add(defendValue)
    }

    @Override
    def afterRemove(Unit unit) {
        unit.armor.subtract(defendValue)
    }
}
