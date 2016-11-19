package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.buff.Buff

/**
 * Created by asus-pc on 2016-10-5.
 */
class HpRegenerationEnhance extends Buff implements Feature{

    static final hpRegenerationValue = 5

    @Override
    def afterAdd(Unit unit) {
        unit.hpRegeneration.enhance(hpRegenerationValue)
    }

    @Override
    def afterRemove(Unit unit) {
        unit.hpRegeneration.weaken(hpRegenerationValue)
    }

}
