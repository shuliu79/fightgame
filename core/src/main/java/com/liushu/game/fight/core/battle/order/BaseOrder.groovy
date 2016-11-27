package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.event.EventFactory


/**
 * Created by asus-pc on 2016-11-18.
 */
abstract class BaseOrder implements Order{

    Player player
    boolean valid = true

    BaseOrder(Player player) {
        this.player = player
    }

    @Override
    def exclusion() {
        player.currentOrder = this.getClass()
    }
}
