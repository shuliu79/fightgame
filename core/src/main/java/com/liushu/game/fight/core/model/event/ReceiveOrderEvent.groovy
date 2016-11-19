package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.battle.order.Order
import com.liushu.game.fight.core.model.Hero

/**
 * Created by asus-pc on 2016-9-17.
 */
class ReceiveOrderEvent extends UnitEvent{

    Order order

    ReceiveOrderEvent(Hero source) {
        super(source)
    }

    @Override
    protected void doExecute() {
        //todo
    }
}
