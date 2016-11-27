package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.battle.order.Order
import com.liushu.game.fight.core.model.LevelModel
import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-10-3.
 */
class EventFactory {

    static BaseEvent createBaseEvent(Class<BaseEvent> clazz){
        def event = clazz.newInstance()
        return event
    }

    static UnitEvent createUnitEvent(Class<UnitEvent> clazz,Unit source){
        def event = clazz.newInstance(source)
//        event.source = source
        return event
    }

    static TargetEvent createTargetEvent(Class<TargetEvent> clazz,Unit source,Unit target){
        def event = clazz.newInstance(source,target)
//        event.source = source
//        event.target = target
        return event
    }

    static HealEvent createHealEvent(Unit source,int value){
        HealEvent event = createUnitEvent(HealEvent,source) as HealEvent
        event.value = value
        return event
    }

    static HurtEvent createHurtEvent(Unit source,Unit target,int hurtType,int value){
        HurtEvent event = createTargetEvent(HurtEvent,source,target) as HurtEvent
        event.value = value
        event.hurtType = hurtType
        return event
    }

    static ExperienceEvent createExperienceEvent(LevelModel source,int value){
        ExperienceEvent event = createUnitEvent(ExperienceEvent,source) as ExperienceEvent
        event.value = value
        return event
    }

    static DeathEvent createDeathEvent(Unit source,UnitEvent cause){
        DeathEvent event = createUnitEvent(DeathEvent,source) as DeathEvent
        event.cause = cause
        return event
    }

    static RoundStartEvent createRoundStartEvent(int round){
        RoundStartEvent event = createBaseEvent(RoundStartEvent) as RoundStartEvent
        return event
    }

    static RoundFinishEvent createRoundFinishEvent(int round){
        RoundFinishEvent event = createBaseEvent(RoundFinishEvent) as RoundFinishEvent
        return event
    }

    static ReceiveOrderEvent createReceiverOrderEvent(Order order,Player player){
        ReceiveOrderEvent event = new ReceiveOrderEvent(player)
        event.order = order
        return event
    }

    static AddGoldEvent createAddGoldEvent(Player player,int value){
        AddGoldEvent event = new AddGoldEvent(player)
        event.value = value
        return event
    }

}
