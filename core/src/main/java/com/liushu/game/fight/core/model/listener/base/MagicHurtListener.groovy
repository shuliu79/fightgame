package com.liushu.game.fight.core.model.listener.base

import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-3.
 */
abstract class MagicHurtListener extends AbstractHurtListener{

    @Override
    protected boolean canHandle_(HurtEvent event) {
        if (event.hurtType.equals(StaticValues.MAGIC_HURT)){
            return true
        }
    }

}
