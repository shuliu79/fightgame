package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.battle.order.Order
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Player

/**
 * Created by asus-pc on 2016-9-17.
 */
class ReceiveOrderEvent extends BaseEvent{

    Order order
    Player player

    ReceiveOrderEvent(Player player) {
        this.player = player
    }

    @Override
    protected void doExecute() {
        //todo
    }

}
