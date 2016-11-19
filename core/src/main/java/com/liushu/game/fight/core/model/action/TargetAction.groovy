package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.TargetEvent

/**
 * Created by asus-pc on 2016-9-20.
 */
abstract class TargetAction extends TargetEvent{
    //类似于baseEvent，但是对应于英雄的指令

    TargetAction(Unit source, Unit target) {
        super(source, target)
    }
}
