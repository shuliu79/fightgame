package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.action.AttackAction
import com.liushu.game.fight.core.model.event.EventFactory
import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.listener.base.AbstractAttackListener
import com.liushu.game.fight.core.model.listener.base.PhysicsHurtListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-3.
 */
class ManaBurnListener extends AbstractAttackListener{

    int burnValue

    ManaBurnListener() {
        priority = StaticValues.VERY_HIGH
    }

    @Override
    void doAfterExecute(AttackAction event) {
        def subtract = event.target.mp.subtract(burnValue)
        def hurtEvent = EventFactory.createHurtEvent(event.source,event.target,StaticValues.PURE_HURT,subtract)
        event.source.executeEvent(hurtEvent)
    }

    @Override
    protected boolean canHandle_(AttackAction event) {
        holder.equals(event.source)
    }
}
