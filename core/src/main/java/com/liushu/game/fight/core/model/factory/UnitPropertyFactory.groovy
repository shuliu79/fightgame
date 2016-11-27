package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.exception.PropertyException
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.IntHeroProperty
import com.liushu.game.fight.core.model.PropertyChangeInfo
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.level.LevelConfig

/**
 * Created by asus-pc on 2016-9-11.
 */
class UnitPropertyFactory {

    IntHeroProperty createMaxAttack(Unit unit) {
        def maxAttack = new IntHeroProperty(1)
        return maxAttack
    }

    IntHeroProperty createMinAttack(Unit unit) {
        def minAttack = new IntHeroProperty(1)
        return minAttack
    }

    IntHeroProperty createMaxHp(Unit unit) {
        def maxHp = new IntHeroProperty(1)
        maxHp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            unit.hp.base = (int)(unit.hp.base*(info.newBase)/(info.oldBase))
        }
        maxHp.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            unit.hp.base = (int)(unit.hp.base*(info.newBase)/(info.oldBase))
        }
        return maxHp
    }

    IntHeroProperty createHp(Unit unit) {
        def hp = new IntHeroProperty(1)
        hp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            if (hp.value>unit.maxHp.value){
                hp.subtract(hp.value-unit.maxHp.value)
            }
        }
        hp.onExtraAdd ={IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            throw new PropertyException("hp should not has extra value")
        }
        return hp
    }

    IntHeroProperty createMaxMp(Unit unit) {
        def maxMp = new IntHeroProperty(1)
        maxMp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            unit.mp.base = (int)(unit.mp.base*(info.newBase)/(info.oldBase))
        }
        maxMp.onBaseSubtract = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            unit.mp.base = (int)(unit.mp.base*(info.newBase)/(info.oldBase))
        }
        return maxMp
    }

    IntHeroProperty createMp(Unit unit) {
        def mp = new IntHeroProperty(1)
        mp.onBaseAdd = {IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            if (mp.value>unit.maxMp.value){
                mp.subtract(mp.value-unit.maxMp.value)
            }
        }
        mp.onExtraAdd ={IntHeroProperty p, PropertyChangeInfo<Integer> info ->
            throw new PropertyException("mp should not has extra value")
        }
        return mp
    }

    IntHeroProperty createHpRegeneration(Unit unit) {
        def hpRegeneration = new IntHeroProperty(0)
        return hpRegeneration
    }

    IntHeroProperty createMpRegeneration(Unit unit) {
        def mpRegeneration = new IntHeroProperty(0)
        return mpRegeneration
    }

    IntHeroProperty createArmor(Unit unit) {
        def armor = new IntHeroProperty(0)
        return armor
    }

    IntHeroProperty createResistance(Unit unit) {
        def resistance = new IntHeroProperty(0)
        return resistance
    }

    def initUnitProperties(Unit unit){
        unit.armor= createArmor(unit)
        unit.resistance = createResistance(unit)
        unit.maxAttack = createMaxAttack(unit)
        unit.minAttack = createMinAttack(unit)
        unit.maxHp = createMaxHp(unit)
        unit.hp = createHp(unit)
        unit.maxMp = createMaxMp(unit)
        unit.mp = createMp(unit)
        unit.hpRegeneration = createHpRegeneration(unit)
        unit.mpRegeneration = createMpRegeneration(unit)
    }

}
