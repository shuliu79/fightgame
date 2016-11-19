package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.buff.Buff

/**
 * Created by asus-pc on 2016-10-5.
 */
class Crazy extends Buff implements Feature{

    @Override
    def afterAdd(Unit unit) {
        unit.maxAttack.enhance(10)
        unit.minAttack.enhance(10)
    }

    @Override
    def afterRemove(Unit unit) {
        unit.maxAttack.weaken(10)
        unit.minAttack.weaken(10)
    }
}
