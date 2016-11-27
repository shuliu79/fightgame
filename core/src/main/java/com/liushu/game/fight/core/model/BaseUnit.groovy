package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.liushu.game.fight.core.model.action.ActionFactory
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.buff.BuffFactory
import com.liushu.game.fight.core.model.buff.BuffPool
import com.liushu.game.fight.core.model.buff.BuffPoolFactory
import com.liushu.game.fight.core.model.buff.SimpleBuffPool
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.EventExecutor
import com.liushu.game.fight.core.utils.RandomUtil
import sun.plugin2.gluegen.runtime.BufferFactory

/**
 * Created by asus-pc on 2016-11-5.
 */
abstract class BaseUnit implements Unit{

//    EventExecutor eventExecutor //负责执行事件
    Team team
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

    BuffPool buffPool
    boolean death = false


    def executeEvent(BaseEvent event){
        team.battle.executeEvent(event,(EventPublisher)team,(EventPublisher)team.getOpponentTeam())
    }

    void attack(Unit target){
        def attackAction = ActionFactory.createAttackAction(this,target)
        executeEvent(attackAction)
    }

    @Override
    def addBuff(int time, Buff buff) {
        buffPool.addBuff(time,buff)
    }

    @Override
    def removeBuff(Buff buff) {
        buffPool.removeBuff(buff)
    }

    def getAttackValue(){
        RandomUtil.nextInt(minAttack.getValue(),maxAttack.getValue())
    }

    @Override
    boolean hasBuff(Class clazz) {
        return buffPool.hasBuff(clazz)
    }

    @Override
    def nextRound() {
        return buffPool.nextRound()
    }

    @Override
    boolean publishEvent(Event event) {
        return buffPool.publishEvent(event)
    }
}
