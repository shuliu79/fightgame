package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.magic.Magic
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-18.
 */
class MagicOrder extends BaseOrder{

    Magic magic
    int priority = StaticValues.LOW

    MagicOrder(Magic magic) {
        this.magic = magic
    }

    @Override
    int getPriority() {
        return priority
    }

    @Override
    def exclusion() {

    }
}
