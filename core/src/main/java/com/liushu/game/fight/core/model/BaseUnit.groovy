package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.model.action.ActionFactory
import com.liushu.game.fight.core.model.buff.BuffPool
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.EventExecutor

/**
 * Created by asus-pc on 2016-11-5.
 */
abstract class BaseUnit implements Unit{

    EventExecutor eventExecutor //负责执行事件

    long id
    String name

    IntHeroProperty armor // 护甲
    IntHeroProperty resistance //魔抗

    IntHeroProperty maxAttack
    IntHeroProperty minAttack
    IntHeroProperty maxHp
    IntHeroProperty hp
    IntHeroProperty maxMp
    IntHeroProperty mp
    IntHeroProperty hpRegeneration
    IntHeroProperty mpRegeneration

    BuffPool buffPool //监听buff等事件
    boolean death = false

    def executeEvent(BaseEvent event){
        eventExecutor.executeEvent(event)
    }

    def attack(Unit target){
        def attackAction = ActionFactory.createAttackAction(this,target)
        executeEvent(attackAction)
    }

}
