package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.RoundFinishEvent
import com.liushu.game.fight.core.model.listener.base.AbstractRoundFinishListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-26.
 */
class UnitRoundFinishListener extends AbstractRoundFinishListener{

    UnitRoundFinishListener(){
        priority = StaticValues.VERY_LOW
    }

    @Override
    protected void doAfterExecute(RoundFinishEvent event) {
        //回复魔法值和生命值
        ((Unit)holder).hp.add(((Unit)holder).hpRegeneration.value)
        ((Unit)holder).mp.add(((Unit)holder).mpRegeneration.value)
        //buff进行清算
        ((Unit)holder).nextRound()
    }
}
