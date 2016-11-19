package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.action.AttackAction
import com.liushu.game.fight.core.model.event.EventFactory
import com.liushu.game.fight.core.model.listener.base.AbstractAttackListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-6.
 */
class MagicPowerListener extends AbstractAttackListener{
    //攻击附魔

    static magicHurt = 10

    @Override
    def doAfterExecute(AttackAction event) {
        def hurtEvent = EventFactory.createHurtEvent(event.source,event.target,StaticValues.MAGIC_HURT,magicHurt)
        event.source.executeEvent(hurtEvent)
    }

    @Override
    protected boolean canHandle_(AttackAction event) {
        holder.equals(event.source)
    }
}
