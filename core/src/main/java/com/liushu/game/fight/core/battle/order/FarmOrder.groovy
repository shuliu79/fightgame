package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-22.
 */
class FarmOrder extends BaseOrder{

    FarmOrder(Player player) {
        super(player)
    }

    @Override
    int getPriority() {
        return StaticValues.HIGH
    }

    @Override
    def exclusion() {
        super.exclusion()
        if (!player.hero.death) {
            player.doFarm()
        }
    }
}
