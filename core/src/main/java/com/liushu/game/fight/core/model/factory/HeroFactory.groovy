package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroType
import com.liushu.game.fight.core.model.buff.BuffPoolFactory
import com.liushu.game.fight.core.model.event.EventExecutor

/**
 * Created by asus-pc on 2016-9-11.
 */
class HeroFactory {

    EventExecutor eventExecutor //负责执行事件，通过spring注入
    HeroPropertyFactory heroPropertyFactory

    def initProperty(Hero hero){
        hero.level = heroPropertyFactory.createLevel(hero)
        hero.experience = heroPropertyFactory.createExperience(hero)
        hero.totalExperience = heroPropertyFactory.createTotalExperience(hero)

        hero.powerGrowth = heroPropertyFactory.createPowerGrowth(hero)
        hero.strengthGrowth = heroPropertyFactory.createStrength(hero)
        hero.mindGrowth = heroPropertyFactory.createMindGrowth(hero)

        hero.power = heroPropertyFactory.createPower(hero)
        hero.strength = heroPropertyFactory.createStrength(hero)
        hero.mind = heroPropertyFactory.createMind(hero)

        hero.armor= heroPropertyFactory.createArmor(hero)
        hero.resistance = heroPropertyFactory.createResistance(hero)
        hero.magicPower = heroPropertyFactory.createMagicPower(hero)
        hero.maxAttack = heroPropertyFactory.createMaxAttack(hero)
        hero.minAttack = heroPropertyFactory.createMinAttack(hero)
        hero.maxHp = heroPropertyFactory.createMaxHp(hero)
        hero.hp = heroPropertyFactory.createHp(hero)
        hero.maxMp = heroPropertyFactory.createMaxMp(hero)
        hero.mp = heroPropertyFactory.createMp(hero)
        hero.hpRegeneration = heroPropertyFactory.createHpRegeneration(hero)
        hero.mpRegeneration = heroPropertyFactory.createMpRegeneration(hero)
        hero.buffPool = BuffPoolFactory.createBuffPool(hero)
        hero.eventExecutor = eventExecutor
    }

    Hero createHero(long id,String name,HeroType type,int power,int strength,int mind){

        if(power<=0 || strength<=0 || mind<=0){
            throw new IllegalArgumentException("基础属性必须大于0")
        }
        def hero = new Hero()
        hero.id = id
        hero.name = name
        hero.type = type

        initProperty(hero)

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
        return hero
    }

}
