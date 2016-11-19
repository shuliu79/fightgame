package com.liushu.game.fight.core.model.magic

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-14.
 */
abstract class Magic {

//    target和source可能是同一个英雄，todo 也可能对所有人都作用
    Unit target
    Unit source
    boolean valid
    boolean aoe

    int priority = StaticValues.LOW

    abstract void execute()

   
}
