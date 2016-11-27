package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.LevelModel
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-9-17.
 */
class HurtEvent extends TargetEvent{

    int value
    int hurtType

    HurtEvent(Unit source, Unit target) {
        super(source, target)
    }

    @Deprecated
    static createEvent(int value,int hurtType,Unit source,Unit target){
        def hurtEvent = new HurtEvent(source,target)
        hurtEvent.value = value
        hurtEvent.hurtType = hurtType
        return hurtEvent
    }

    @Override
    protected void doExecute() {
        def factValue = 0
        if (hurtType.equals(StaticValues.PHYSICS_HURT)){
            factValue = value-target.armor.value
        }else if (hurtType.equals(StaticValues.MAGIC_HURT)){
            factValue = (int)(value*(1-target.resistance/100))
        }else if (hurtType.equals(StaticValues.PURE_HURT)){
            factValue = value
        }
        if (factValue>0) {
            target.hp.subtract(factValue)
        }
        if (source instanceof LevelModel) {
            def experienceEvent = EventFactory.createExperienceEvent(source, factValue)
            source.executeEvent(experienceEvent)
        }
        if (target.hp.value<=0){
            def deathEvent = EventFactory.createDeathEvent(target,this)
            target.executeEvent(deathEvent)
        }
    }


}
