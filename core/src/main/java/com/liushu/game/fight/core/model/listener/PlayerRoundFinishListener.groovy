package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.RoundFinishEvent
import com.liushu.game.fight.core.model.listener.base.AbstractRoundFinishListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-26.
 */
class PlayerRoundFinishListener extends AbstractRoundFinishListener{

    PlayerRoundFinishListener(){
        priority = StaticValues.VERY_LOW
    }

    @Override
    protected void doAfterExecute(RoundFinishEvent event) {
        ((Player)holder).gold+=((Player)holder).goldPerTurn
        ((Player)holder).currentOrder = null
    }
}
