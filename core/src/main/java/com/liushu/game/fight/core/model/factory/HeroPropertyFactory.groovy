package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.IntHeroProperty
import com.liushu.game.fight.core.model.PropertyChangeInfo
import com.liushu.game.fight.core.system.level.LevelConfig

/**
 * Created by asus-pc on 2016-9-11.
 */
class HeroPropertyFactory extends UnitPropertyFactory{

    def strengthHPFactor = 13
    def mindMPFactor = 9
    def powerMaxAttackFactor = 4/3f
    def strengthHPRegeneration = 1/3f
    def mindMPRegeneration = 1/3f

    IntHeroProperty createLevel(Hero hero) {

        def level = new IntHeroProperty(1)
        level.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            //经验值减掉级需要经验值
            //按照成长增加属性
            hero.experience.subtract(LevelConfig.levelExperience[p.value])
            hero.power.add(hero.powerGrowth.value)
            hero.strength.add(hero.strengthGrowth.value)
            hero.mind.add(hero.mindGrowth.value)
        }
        return level

    }

    IntHeroProperty createExperience(Hero hero) {

        def experience = new IntHeroProperty(0)
        experience.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            //更新总经验，检查是否升级
            hero.totalExperience.add(info.baseChange)
            if (hero.level.base == LevelConfig.maxLevel) {
                p.base = 0
                return
            } else {
                while (LevelConfig.levelExperience[hero.level.value+1] <= p.value) {
                    hero.level.add(1)
                    if (hero.level.base == LevelConfig.maxLevel) {
                        p.base = 0
                        return
                    }
                }
            }
        }

//        experience.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (p.base < 0) {
//                p.base = 0
//            }
//        }
        return experience
    }

    IntHeroProperty createTotalExperience(Hero hero) {
        def total = new IntHeroProperty(0)
        total.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            if (p.base > LevelConfig.maxExperience) {
                p.base = LevelConfig.maxExperience
            }
        }
        return total
    }

    IntHeroProperty createPowerGrowth(Hero hero) {
        //变化会引起力量的相应变化，白字对白字，绿字对绿字
        def pg = new IntHeroProperty(0)
        pg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.power.add(hero.level.value * info.baseChange)
        }
        pg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.power.subtract(-hero.level.value * info.baseChange)
        }
        pg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.power.enhance(hero.level.value * info.extraChange)
        }
        pg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.power.weaken(-hero.level.value * info.extraChange)
        }
        return pg
    }

    IntHeroProperty createStrengthGrowth(Hero hero) {
        def sg = new IntHeroProperty(0)
        sg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.strength.add(hero.level.value * info.baseChange)
        }
        sg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.strength.subtract(-hero.level.value * info.baseChange)
        }
        sg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.strength.enhance(hero.level.value * info.extraChange)
        }
        sg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.strength.weaken(-hero.level.value * info.extraChange)
        }
        return sg
    }

    IntHeroProperty createMindGrowth(Hero hero) {
        def mg = new IntHeroProperty(0)
        mg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.mind.add(hero.level.value * info.baseChange)
        }
        mg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.mind.subtract(-hero.level.value * info.baseChange)
        }
        mg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.mind.enhance(hero.level.value * info.extraChange)
        }
        mg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.mind.weaken(-hero.level.value * info.extraChange)
        }
        return mg
    }

    protected getMaxAttack(int num) {
        return (int) (num * powerMaxAttackFactor)
    }

    protected getHPRegeneration(int num){
        return (int)(num * strengthHPRegeneration)
    }

    protected getMPRegeneration(int num){
        return (int)(num * mindMPRegeneration)
    }

    IntHeroProperty createPower(Hero hero) {
        def power = new IntHeroProperty(0)
        def valueAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.minAttack.add(info.valueChange)
            hero.maxAttack.add(getMaxAttack(info.newValue) - getMaxAttack(info.oldValue))
        }
        def valueSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.minAttack.enhance(info.valueChange)
            hero.maxAttack.enhance(getMaxAttack(info.newValue) - getMaxAttack(info.oldValue))
        }
        power.onBaseAdd = valueAdd
        power.onBaseSubtract = valueAdd
        power.onExtraAdd = valueSubtract
        power.onExtraSubtract = valueSubtract
        return power
    }

    IntHeroProperty createStrength(Hero hero) {
        def strength = new IntHeroProperty(0)
        strength.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxHp.add(strengthHPFactor*info.baseChange)
            hero.hpRegeneration.add(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase))
        }
        strength.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxHp.subtract(-strengthHPFactor*info.baseChange)
            hero.hpRegeneration.subtract(-(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase)))
        }
        strength.onExtraAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxHp.add(strengthHPFactor*info.baseChange)
            hero.hpRegeneration.add(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase))
        }
        strength.onExtraSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxHp.subtract(-strengthHPFactor*info.baseChange)
            hero.hpRegeneration.subtract(-(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase)))
        }
        return strength
    }

    IntHeroProperty createMind(Hero hero) {
        def mind = new IntHeroProperty(0)
        mind.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxMp.add(mindMPFactor*info.baseChange)
            hero.mpRegeneration.add(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase))
        }
        mind.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxMp.subtract(-mindMPFactor*info.baseChange)
            hero.mpRegeneration.subtract(-(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase)))
        }
        mind.onExtraAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxMp.add(mindMPFactor*info.baseChange)
            hero.mpRegeneration.add(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase))
        }
        mind.onExtraSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            hero.maxMp.subtract(-mindMPFactor*info.baseChange)
            hero.mpRegeneration.subtract(-(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase)))
        }
        return mind
    }


    IntHeroProperty createMagicPower(Hero hero) {
        def magicPower = new IntHeroProperty(0)
        return magicPower
    }

    def initHeroProperties(Hero hero){
        hero.level = createLevel(hero)
        hero.experience = createExperience(hero)
        hero.totalExperience = createTotalExperience(hero)

        hero.powerGrowth = createPowerGrowth(hero)
        hero.strengthGrowth = createStrengthGrowth(hero)
        hero.mindGrowth = createMindGrowth(hero)

        hero.power = createPower(hero)
        hero.strength = createStrength(hero)
        hero.mind = createMind(hero)
        hero.magicPower = createMagicPower(hero)

        initUnitProperties(hero)

    }

}
