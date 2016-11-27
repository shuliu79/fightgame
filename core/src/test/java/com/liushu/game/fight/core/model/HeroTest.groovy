package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.exception.PropertyException
import com.liushu.game.fight.core.model.helpers.BattleHelper
import com.liushu.game.fight.core.model.helpers.HeroHelper
import com.liushu.game.fight.core.model.factory.BattleFactory
import com.liushu.game.fight.core.model.factory.HeroFactory
import com.liushu.game.fight.core.model.factory.HeroPropertyFactory
import com.liushu.game.fight.core.model.factory.PlayerFactory
import com.liushu.game.fight.core.model.factory.TeamFactory
import com.liushu.game.fight.core.system.level.LevelConfig
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by asus-pc on 2016-11-26.
 */
@Unroll
class HeroTest extends Specification{

    HeroFactory heroFactory = FactoryHolder.instance.heroFactory
    HeroPropertyFactory propertyFactory = FactoryHolder.instance.propertyFactory
    BattleFactory battleFactory = FactoryHolder.instance.battleFactory
    PlayerFactory playerFactory = FactoryHolder.instance.playerFactory
    TeamFactory teamFactory = FactoryHolder.instance.teamFactory
    def heroHelper = new HeroHelper()
    def battleHelper = new BattleHelper()

    def "test init hero"(){
        setup:"create hero with hero factory"
        println "test hero ${name}"
        Hero hero = heroFactory.createHero(id,name,HeroType.BLANK,power,strength,mind)

        expect:"hero property should be init"
        hero.level.value == 1
        hero.experience.value == 0
        hero.totalExperience.value == 0
        hero.powerGrowth.value != 0
        hero.strengthGrowth.value != 0
        hero.mindGrowth.value != 0
        hero.power.base == power+hero.powerGrowth.value
        hero.strength.base == strength+hero.strengthGrowth.value
        hero.mind.base == mind+hero.mindGrowth.value
        hero.magicPower.base == 0
        hero.armor.value == 0 // 护甲
        hero.resistance.value == 0 //魔抗
        hero.maxAttack.value == 1+(hero.power.value*propertyFactory.powerMaxAttackFactor).toInteger()
        hero.minAttack.value == 1+(hero.power.value).toInteger()
        hero.maxHp.value == 1+hero.strength.value*propertyFactory.strengthHPFactor
        hero.hp.value == 1+hero.strength.value*propertyFactory.strengthHPFactor
        hero.maxMp.value == 1+hero.mind.value*propertyFactory.mindMPFactor
        hero.mp.value == 1+hero.mind.value*propertyFactory.mindMPFactor
        hero.hpRegeneration.value == (hero.strength.value*propertyFactory.strengthHPRegeneration).toInteger()
        hero.mpRegeneration.value == (hero.mind.value*propertyFactory.mindMPRegeneration).toInteger()
        !hero.death

        where:
        id | name | power | strength || mind
        1 | "test_hero1" | 30 | 40 || 50
        2 | "test_hero2" | 40 | 40 || 40
        3 | "test_hero3" | 50 | 40 || 30
        4 | "test_hero4" | 1 | 1 || 1
        5 | "test_hero5" | 10 | 10 || 10
        6 | "test_hero6" | 20 | 20 || 20
        7 | "test_hero7" | 50 | 40 || 30
        8 | "test_hero8" | 22 | 51 || 33

    }

    def "one hero attack another hero"(){
        setup:"create two hero with hero factory"
        Hero defendHero = heroFactory.createHero(1,"defendHero",HeroType.BLANK,30,30,30)
        Hero attackHero = heroFactory.createHero(2,"attackHero",HeroType.BLANK,power,strength,mind)

        def defenderHp = defendHero.hp.value
        //attack hero 可能会升级
        def attackerMinAttack = attackHero.minAttack.value
        def attackerMaxAttack = attackHero.maxAttack.value
        def battle = battleHelper.createBattle(attackHero,defendHero)

        when:"attack hero attack the defend hero"
        attackHero.attack(defendHero)
        attackHero.defend()
        then:"defend hero and lose hp attack hero gain experience"
        defendHero.hp.value <= defenderHp-attackerMinAttack
        defendHero.hp.value >= defenderHp-attackerMaxAttack
        attackHero.totalExperience.value == defenderHp - defendHero.hp.value

        where:
        power | strength || mind
        10 | 10 | 10
        20 | 20 | 20
        30 | 30 | 30
    }

    def "hero hp(mp) must less than or equal it's maxHp(maxMp)"(){
        setup:"create a hero"
        def hero = heroHelper.createSimpleHero(1)

        when:"hero gain hp(mp)"
        hero.hp.add(10)
        hero.mp.add(10)

        then:"hero hp equal it's maxHp(maxMp)"
        hero.hp.value == hero.maxHp.value
        hero.mp.value == hero.maxMp.value

    }

    def "hero hp must not has extra value"(){
        setup:"create a hero"
        def hero = heroHelper.createSimpleHero(1)

        when:"hero gain extra hp"
        hero.hp.enhance(10)

        then:"throw property exception"
        thrown(PropertyException)
    }

    def "hero mp must not has extra value"(){
        setup:"create a hero"
        def hero = heroHelper.createSimpleHero(1)

        when:"hero gain extra mp"
        hero.mp.enhance(10)

        then:"throw property exception"
        thrown(PropertyException)
    }

    def "hero will level up when gain the experience enough"(){
        setup:"create a hero"
        def hero = heroHelper.createSimpleHero(1)

        when:"hero gain some experience"
        def oldPower = hero.power.base
        def oldStrength = hero.strength.base
        def oldMind = hero.mind.base
        hero.experience.add(a)

        then:"hero level change"
        hero.level.value == b
        hero.power.base == oldPower+hero.powerGrowth.value*(hero.level.value-1)
        hero.mind.base == oldMind+hero.mindGrowth.value*(hero.level.value-1)
        hero.strength.base == oldStrength+hero.strengthGrowth.value*(hero.level.value-1)

        where:
        a || b
        50 || 1
        100 || 2
        200 || 2
        300 || 3
        400 || 3
        450 || 4
    }

    def "test hero gain experience more than max experience"(){
        setup:"create a hero"
        def hero = heroHelper.createSimpleHero(1)

        when:"hero gain much experience"
        hero.experience.add(20000)

        then:"hero's experience become the max value and level become the max level"
        hero.level.value == LevelConfig.maxLevel
        hero.experience.value == 0
        hero.totalExperience.value == LevelConfig.maxExperience
    }

    def "test hero under attack and dead"(){
        setup:"create two hero and a battle"
        def attackHero = heroHelper.createSimpleHero(1)
        def defendHero = heroFactory.createHero(2,"defendHero",HeroType.BLANK,1,1,1)
        def battle = battleHelper.createBattle(attackHero,defendHero)

        when:"attack hero attack the defend hero"
        attackHero.attack(defendHero)

        then:"defend hero dead"
        defendHero.hp.value == 0
        defendHero.death
    }

}
