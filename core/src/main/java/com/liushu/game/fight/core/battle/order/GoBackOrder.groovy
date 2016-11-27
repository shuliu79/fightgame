package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.event.ExperienceEvent
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-22.
 */
class GoBackOrder extends BaseOrder{
    GoBackOrder(Player player) {
        super(player)
    }

    @Override
    int getPriority() {
        return StaticValues.VERY_HIGH
    }

    @Override
    def exclusion() {
        super.exclusion()
        return null //todo
    }
}
