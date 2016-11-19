package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-9-30.
 */
abstract class UnitEvent extends BaseEvent{

    Unit source

    UnitEvent(Unit source) {
        this.source = source
    }

}
