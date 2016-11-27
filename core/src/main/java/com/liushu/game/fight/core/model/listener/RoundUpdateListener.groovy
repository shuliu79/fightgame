package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.Battle
import com.liushu.game.fight.core.model.event.RoundStartEvent
import com.liushu.game.fight.core.model.listener.base.AbstractRoundStartListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-26.
 */
class RoundUpdateListener extends AbstractRoundStartListener{

    RoundUpdateListener() {
        priority = StaticValues.HIGHEST
    }

    @Override
    protected void doBeforeExecute(RoundStartEvent event) {
        (holder as Battle).round+=1
    }
}
