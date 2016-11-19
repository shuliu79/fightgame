package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.listener.base.AbstractHurtListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-17.
 */
class MagicHurtImmuneListener extends AbstractHurtListener{

    @Override
    protected boolean canHandle_(HurtEvent event) {
        holder.equals(event.target) && event.hurtType.equals(StaticValues.MAGIC_HURT)
    }

    @Override
    protected void doBeforeExecute(HurtEvent event) {
        //免疫这次伤害
        event.valid = false
    }
}
