package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.UnitEvent

/**
 * Created by asus-pc on 2016-9-30.
 */
abstract class UnitAction extends UnitEvent{

    UnitAction(Unit source) {
        super(source)
    }
}
