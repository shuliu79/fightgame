package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.EventFactory
import com.liushu.game.fight.core.system.StaticValues
import com.liushu.game.fight.core.utils.RandomUtil

/**
 * Created by asus-pc on 2016-9-20.
 * common attack action,both holder and creature
 */
class AttackAction extends TargetAction{

    int addition = 0

    AttackAction(Unit source, Unit target) {
        super(source, target)
    }

    @Override
    protected void doExecute() {

        def attack = source.getAttackValue()+addition
        if (attack>0){
            def hurtEvent = EventFactory.createHurtEvent(source,target,StaticValues.PHYSICS_HURT,attack)
            target.executeEvent(hurtEvent)
        }

    }
}
