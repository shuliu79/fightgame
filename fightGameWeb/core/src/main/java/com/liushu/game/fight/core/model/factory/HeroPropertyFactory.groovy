package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.IntHeroProperty
import com.liushu.game.fight.core.model.PropertyChangeInfo
import com.liushu.game.fight.core.system.level.LevelConfig
import com.sun.xml.internal.bind.v2.model.core.PropertyInfo

/**
 * Created by asus-pc on 2016-9-11.
 */
class HeroPropertyFactory {

    final static strengthHPFactor = 13
    final static mindMPFactor = 9
    final static powerMaxAttackFactor = 4/3f
    final static strengthHPRegeneration = 1/3f
    final static mindMPRegeneration = 1/3f

    IntHeroProperty createLevel() {

        def level = new IntHeroProperty(1)
        level.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            //经验值减掉级需要经验值
            //按照成长增加属性
            def hero = delegate as Hero
            hero.experience.subtract(LevelConfig.levelExperience[p.value])
            hero.power.add(hero.powerGrowth.value)
            hero.strength.add(hero.strengthGrowth.value)
            hero.mind.add(hero.mind.value)
        }
        return level

    }

    IntHeroProperty createExperience() {

        def experience = new IntHeroProperty(0)
        experience.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            //更新总经验，检查是否升级
            def hero = delegate as Hero
            hero.totalExperience.add(info.baseChange)
            if (hero.level.base == LevelConfig.maxLevel) {
                p.base = 0
                return
            } else {
                while (LevelConfig.levelExperience[hero.level.value] <= p.value) {
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

    IntHeroProperty createTotalExperience() {
        def total = new IntHeroProperty(0)
        total.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            if (p.base > LevelConfig.maxExperience) {
                p.base = LevelConfig.maxExperience
            }
        }
        return total
    }

    IntHeroProperty createPowerGrowth() {
        //变化会引起力量的相应变化，白字对白字，绿字对绿字
        def pg = new IntHeroProperty(0)
        pg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.power.add(hero.level.value * info.baseChange)
        }
        pg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.power.subtract(-hero.level.value * info.baseChange)
        }
        pg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.power.enhance(hero.level.value * info.extraChange)
        }
        pg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.power.weaken(-hero.level.value * info.extraChange)
        }
        return pg
    }

    IntHeroProperty createStrengthGrowth() {
        def sg = new IntHeroProperty(0)
        sg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.strength.add(hero.level.value * info.baseChange)
        }
        sg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.strength.subtract(-hero.level.value * info.baseChange)
        }
        sg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.strength.enhance(hero.level.value * info.extraChange)
        }
        sg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.strength.weaken(-hero.level.value * info.extraChange)
        }
        return sg
    }

    IntHeroProperty createMindGrowth() {
        def mg = new IntHeroProperty(0)
        mg.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.mind.add(hero.level.value * info.baseChange)
        }
        mg.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newBase < 0) {
//                info.newBase = 0
//            }
            hero.mind.subtract(-hero.level.value * info.baseChange)
        }
        mg.onExtraAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.mind.enhance(hero.level.value * info.extraChange)
        }
        mg.onExtraSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newExtra < 0) {
//                info.newExtra = 0
//            }
            hero.mind.weaken(-hero.level.value * info.extraChange)
        }
        return mg
    }

    protected static getMaxAttack(int num) {
        return (int) (num * powerMaxAttackFactor)
    }

    protected static getHPRegeneration(int num){
        return (int)(num * strengthHPRegeneration)
    }

    protected static getMPRegeneration(int num){
        return (int)(num * mindMPRegeneration)
    }

    IntHeroProperty createPower() {
        def power = new IntHeroProperty(0)
        power.onBaseAdd = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.minAttack.add(info.baseChange)
            hero.maxAttack.add(getMaxAttack(info.newBase) - getMaxAttack(info.oldBase))
        }
        power.onBaseSubtract = { IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newBase < 0){
//                info.newBase = 0
//            }
            hero.minAttack.subtract(-info.baseChange)
            hero.maxAttack.subtract(-(getMaxAttack(info.newBase) - getMaxAttack(info.oldBase)))
        }
        power.onExtraAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.minAttack.enhance(info.baseChange)
            hero.maxAttack.enhance(getMaxAttack(info.newBase) - getMaxAttack(info.oldBase))
        }
        power.onExtraSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
//            if (info.newBase < 0){
//                info.newBase = 0
//            }
            hero.minAttack.weaken(-info.baseChange)
            hero.maxAttack.weaken(-(getMaxAttack(info.newBase) - getMaxAttack(info.oldBase)))
        }
        return power
    }

    IntHeroProperty createStrength() {
        def strength = new IntHeroProperty(0)
        strength.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxHp.add(strengthHPFactor*info.baseChange)
            hero.hpRegeneration.add(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase))
        }
        strength.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxHp.subtract(-strengthHPFactor*info.baseChange)
            hero.hpRegeneration.subtract(-(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase)))
        }
        strength.onExtraAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxHp.add(strengthHPFactor*info.baseChange)
            hero.hpRegeneration.add(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase))
        }
        strength.onExtraSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxHp.subtract(-strengthHPFactor*info.baseChange)
            hero.hpRegeneration.subtract(-(getHPRegeneration(info.newBase)-getHPRegeneration(info.oldBase)))
        }
        return strength
    }

    IntHeroProperty createMind() {
        def mind = new IntHeroProperty(0)
        mind.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxMp.add(mindMPFactor*info.baseChange)
            hero.mpRegeneration.add(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase))
        }
        mind.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxMp.subtract(-mindMPFactor*info.baseChange)
            hero.mpRegeneration.subtract(-(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase)))
        }
        mind.onExtraAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxMp.add(mindMPFactor*info.baseChange)
            hero.mpRegeneration.add(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase))
        }
        mind.onExtraSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.maxMp.subtract(-mindMPFactor*info.baseChange)
            hero.mpRegeneration.subtract(-(getMPRegeneration(info.newBase)-getMPRegeneration(info.oldBase)))
        }
        return mind
    }

    IntHeroProperty createMaxAttack() {
        def maxAttack = new IntHeroProperty(1)
        return maxAttack
    }

    IntHeroProperty createMinAttack() {
        def minAttack = new IntHeroProperty(1)
        return minAttack
    }

    IntHeroProperty createMaxHp() {
        def maxHp = new IntHeroProperty(1)
        maxHp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.hp.base = (int)(hero.hp.base*(info.newBase)/(info.oldBase))
        }
        maxHp.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.hp.base = (int)(hero.hp.base*(info.newBase)/(info.oldBase))
        }
        return maxHp
    }

    IntHeroProperty createHp() {
        def hp = new IntHeroProperty(1)
        return hp
    }

    IntHeroProperty createMaxMp() {
        def maxMp = new IntHeroProperty(1)
        maxMp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.mp.base = (int)(hero.mp.base*(info.newBase)/(info.oldBase))
        }
        maxMp.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            def hero = delegate as Hero
            hero.mp.base = (int)(hero.mp.base*(info.newBase)/(info.oldBase))
        }
        return maxMp
    }

    IntHeroProperty createMp() {
        def mp = new IntHeroProperty(1)
        return mp
    }

    IntHeroProperty createHpRegeneration() {
        def hpRegeneration = new IntHeroProperty(0)
        return hpRegeneration
    }

    IntHeroProperty createMpRegeneration() {
        def mpRegeneration = new IntHeroProperty(0)
        return mpRegeneration
    }

    IntHeroProperty createArmor() {
        def armor = new IntHeroProperty(0)
        return armor
    }

    IntHeroProperty createResistance() {
        def resistance = new IntHeroProperty(0)
        return resistance
    }

    IntHeroProperty createMagicPower() {
        def magicPower = new IntHeroProperty(0)
        return magicPower
    }

}
