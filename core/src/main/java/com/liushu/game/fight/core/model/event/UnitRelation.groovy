package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-9-29.
 */
interface UnitRelation {

    Unit getTarget()
    Unit getSource()

}
