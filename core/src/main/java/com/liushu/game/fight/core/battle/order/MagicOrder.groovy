package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.magic.Magic
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-18.
 */
class MagicOrder extends BaseOrder{

    Magic magic
    int priority = StaticValues.LOW

    MagicOrder(Player player,Magic magic) {
        super(player)
        this.magic = magic
    }

    @Override
    int getPriority() {
        super.exclusion()
        return priority
    }

    @Override
    def exclusion() {
        //todo
    }
}
