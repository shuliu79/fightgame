package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Hero

/**
 * Created by asus-pc on 2016-9-20.
 */
class HealEvent extends UnitEvent{

    int value

    HealEvent(Hero source) {
        super(source)
    }

    @Override
    protected void doExecute() {
        source.hp.add(value)
    }
}
