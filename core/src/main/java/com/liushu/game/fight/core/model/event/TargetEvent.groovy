package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-9-30.
 */
abstract class TargetEvent extends UnitEvent implements UnitRelation{

    Unit target

    TargetEvent(Unit source, Unit target) {
        super(source)
        this.target = target
    }
}
