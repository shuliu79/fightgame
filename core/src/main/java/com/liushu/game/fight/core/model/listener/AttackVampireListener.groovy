package com.liushu.game.fight.core.model.listener

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.liushu.game.fight.core.model.event.EventFactory
import com.liushu.game.fight.core.model.event.HealEvent
import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.listener.base.AbstractHurtListener
import com.liushu.game.fight.core.model.listener.base.PhysicsHurtListener
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-3.
 */
class AttackVampireListener extends PhysicsHurtListener{

    double rate = 0.05

    @Override
    protected boolean canHandle_(HurtEvent event) {
        holder.equals(event.source)
    }

    @Override
    void doAfterExecute(HurtEvent event) {
        int vampireValue = (int)(event.value * rate)
        def healEvent = EventFactory.createHealEvent(event.source,vampireValue)
        event.source.executeEvent(healEvent)
    }

}
