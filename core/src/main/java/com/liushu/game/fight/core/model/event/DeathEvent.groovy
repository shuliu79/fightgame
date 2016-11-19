package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-9-17.
 */
class DeathEvent extends UnitEvent{

    UnitEvent cause

    DeathEvent(Unit source) {
        super(source)
    }

    @Override
    protected void doExecute() {
        source.death = true
        //todo 中断其他行动
    }

}
