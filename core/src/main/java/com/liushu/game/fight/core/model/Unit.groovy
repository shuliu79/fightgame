package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.buff.BuffPool
import com.liushu.game.fight.core.model.event.BaseEvent

/**
 * Created by asus-pc on 2016-11-5.
 */
interface Unit extends BuffPool {

//    EventExecutor getEventExecutor()

    void attack(Unit unit)

//    void setEventExecutor(EventExecutor eventExecutor)

    long getId()

    void setId(long id)

    String getName()

    void setName(String name)

    IntHeroProperty getArmor()

    void setArmor(IntHeroProperty armor)

    IntHeroProperty getResistance()

    void setResistance(IntHeroProperty resistance)

    IntHeroProperty getMaxAttack()

    void setMaxAttack(IntHeroProperty maxAttack)

    IntHeroProperty getMinAttack()

    void setMinAttack(IntHeroProperty minAttack)

    IntHeroProperty getMaxHp()

    void setMaxHp(IntHeroProperty maxHp)

    IntHeroProperty getHp()

    void setHp(IntHeroProperty hp)

    IntHeroProperty getMaxMp()

    void setMaxMp(IntHeroProperty maxMp)

    IntHeroProperty getMp()

    void setMp(IntHeroProperty mp)

    IntHeroProperty getHpRegeneration()

    void setHpRegeneration(IntHeroProperty hpRegeneration)

    IntHeroProperty getMpRegeneration()

    void setMpRegeneration(IntHeroProperty mpRegeneration)

//    BuffPool getBuffPool()//todo 将buffPool改为私有对象，只暴露一些必要的方法，不能直接调用publish方法

//    void setBuffPool(BuffPool buffPool)

    boolean isDeath()

    void setDeath(boolean death)

    def executeEvent(BaseEvent event)

    def addBuff(int time,Buff buff)

    def removeBuff(Buff buff)

    def getAttackValue()

}
