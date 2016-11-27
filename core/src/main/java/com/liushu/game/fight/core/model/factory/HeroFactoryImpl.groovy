package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroImpl
import com.liushu.game.fight.core.model.HeroType
import com.liushu.game.fight.core.model.buff.BuffPoolFactory

/**
 * Created by asus-pc on 2016-9-11.
 */
class HeroFactoryImpl implements HeroFactory{

//    EventExecutor eventExecutor //负责执行事件，通过spring注入
    HeroPropertyFactory heroPropertyFactory
    EventPublisherFactory eventPublisherFactory
    BuffPoolFactory buffPoolFactory
    List<Listener> defaultBattleListeners

    protected initBattlePublisher(EventPublisher publisher){
        if (defaultBattleListeners!=null) {
            defaultBattleListeners.each {
                publisher.registerListener(it)
            }
        }
    }

//    protected initProperty(Hero hero){
//        heroPropertyFactory.initHeroProperties(hero)
//    }

    Hero createHero(long id,String name,HeroType type,int power,int strength,int mind){

        if(power<=0 || strength<=0 || mind<=0){
            throw new IllegalArgumentException("基础属性必须大于0")
        }
        def hero = new HeroImpl()
        hero.id = id
        hero.name = name
        hero.type = type

        heroPropertyFactory.initHeroProperties(hero)

        def growth = HeroType.getGrowth(type)
        hero.powerGrowth.add(growth.power)
        hero.strengthGrowth.add(growth.strength)
        hero.mindGrowth.add(growth.mind)

        hero.power.add(power)
        hero.strength.add(strength)
        hero.mind.add(mind)

        def heroTypeRational = HeroType.getHeroTypeRational(type)
        hero.power.rationalAdd(heroTypeRational.power?:0)
        hero.strength.rationalAdd(heroTypeRational.strength?:0)
        hero.mind.rationalAdd(heroTypeRational.mind?:0)

        hero.eventPublisher = eventPublisherFactory.createPublisher(hero)
        hero.buffPool = buffPoolFactory.createBuffPool(hero)
        return hero
    }

}
