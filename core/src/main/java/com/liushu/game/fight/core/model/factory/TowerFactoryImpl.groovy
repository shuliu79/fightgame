package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Team
import com.liushu.game.fight.core.model.Tower
import com.liushu.game.fight.core.model.TowerImpl
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.buff.BuffPool
import com.liushu.game.fight.core.model.buff.BuffPoolFactory
import com.liushu.game.fight.core.model.event.SimpleEventPublisher
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-13.
 */
class TowerFactoryImpl implements TowerFactory{

    //塔的buffer
    //反击，当被攻击的时候反击
    //魔法抵抗（免疫所有指向性魔法和魔法伤害的作用）
    //魔法看做是event的一种，通过设置event的valid属性来禁用魔法

    List<Listener> defaultTowerListeners
    EventPublisherFactory eventPublisherFactory
    UnitPropertyFactory unitPropertyFactory
    BuffPoolFactory buffPoolFactory

    protected initTowerLister(EventPublisher publisher){
        if (defaultTowerListeners!=null){
            defaultTowerListeners.each {
                publisher.registerListener(it)
            }
        }
    }

    protected createTowerWithConfig(config) {
        def tower = new TowerImpl()
        unitPropertyFactory.initUnitProperties(tower)
        tower.armor.add(config.armor)
        tower.maxAttack.add(config.maxAttack)
        tower.minAttack.add(config.minAttack)
        tower.maxHp.add(config.maxHp)
        tower.hp.add(config.hp)
        tower.maxMp.add(config.maxMp)
        tower.mp.add(config.mp)
        tower.eventPublisher = eventPublisherFactory.createPublisher(tower)
        tower.buffPool = buffPoolFactory.createBuffPool(tower)
        initTowerLister(tower)
        return tower
    }


    protected outerTowerConfig = [
            armor    : 5,
            maxAttack: 50,
            minAttack: 50,
            maxHp    : 200,
            hp       : 200,
            maxMp    : 0,
            mp       : 0
    ]

    protected middleTowerConfig = [
            armor    : 7,
            maxAttack: 70,
            minAttack: 70,
            maxHp    : 250,
            hp       : 250,
            maxMp    : 0,
            mp       : 0
    ]

    protected mainTowerConfig = [
            armor    : 10,
            maxAttack: 100,
            minAttack: 100,
            maxHp    : 300,
            hp       : 300,
            maxMp    : 0,
            mp       : 0
    ]

    Tower createOuterTower() {
        return createTowerWithConfig(outerTowerConfig)
    }

    Tower createMiddleTower() {
        return createTowerWithConfig(middleTowerConfig)
    }

    Tower createMainTower() {
        return createTowerWithConfig(mainTowerConfig)
    }

}
