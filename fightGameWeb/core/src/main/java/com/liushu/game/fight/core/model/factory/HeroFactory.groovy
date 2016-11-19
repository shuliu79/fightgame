package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroType

/**
 * Created by asus-pc on 2016-9-11.
 */
class HeroFactory {

    HeroPropertyFactory heroPropertyFactory

    def initProperty(Hero hero){
        hero.level = heroPropertyFactory.createLevel()
        hero.experience = heroPropertyFactory.createExperience()
        hero.totalExperience = heroPropertyFactory.createTotalExperience()

        def growth = HeroType.getGrowth(type)
        hero.powerGrowth = heroPropertyFactory.createPowerGrowth()
        hero.strengthGrowth = heroPropertyFactory.createStrength()
        hero.mindGrowth = heroPropertyFactory.createMindGrowth()

        hero.power = heroPropertyFactory.createPower()
        hero.strength = heroPropertyFactory.createStrength()
        hero.mind = heroPropertyFactory.createMind()

        hero.armor= heroPropertyFactory.createArmor()
        hero.resistance = heroPropertyFactory.createResistance()
        hero.magicPower = heroPropertyFactory.createMagicPower()
        hero.maxAttack = heroPropertyFactory.createMaxAttack()
        hero.minAttack = heroPropertyFactory.createMinAttack()
        hero.maxHp = heroPropertyFactory.createMaxHp()
        hero.hp = heroPropertyFactory.createHp()
        hero.maxMp = heroPropertyFactory.createMaxMp()
        hero.mp = heroPropertyFactory.createMp()
        hero.hpRegeneration = heroPropertyFactory.createHpRegeneration()
        hero.mpRegeneration = heroPropertyFactory.createMpRegeneration()

    }

    def createHero(long id,String name,HeroType type,int power,int strength,int mind){

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

    }

}
