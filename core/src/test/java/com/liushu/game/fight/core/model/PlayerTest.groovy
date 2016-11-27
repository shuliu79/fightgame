package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.battle.order.GoBackOrder
import com.liushu.game.fight.core.model.helpers.BattleHelper
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by asus-pc on 2016-11-27.
 */
@Unroll
class PlayerTest extends Specification{

    def orderFactory = FactoryHolder.instance.orderFactory
    def battleHelper = new BattleHelper()
    def battle = battleHelper.createSimpleBattle()
    def player1 = battle.players[0]
    def player2 = battle.players[1]

    def "test hero farm"() {

        when: "player1 execute farm"
        def gold = player1.gold
        def experience = player1.hero.totalExperience.value
        def farmOrder = orderFactory.createFarmOrder(player1)
        farmOrder.exclusion()

        then: "player gain gold and experience"
        player1.gold <= gold+player1.getMaxFarmGoldValue()
        player1.gold >= gold+player1.getMinFarmGoldValue()
        player1.hero.totalExperience.value >= experience+player1.getMinFarmExperienceValue()
        player1.hero.totalExperience.value <= experience+player1.getMaxFarmExperienceValue()
    }

    def "player1 gank player2"(){

        setup:
        player1.hero = Mock(Hero)

        when:"player1 execute gank"
        def gankOrder = orderFactory.createGankOrder(player1)
        gankOrder.exclusion()

        then:"player2 attacked"
        1 * player1.hero.attack(player2.hero)

    }

    def "player1 gank player2 when player2 goback"(){
        setup:
        player1.hero = Mock(Hero)
        player2.currentOrder = GoBackOrder.class

        when:"player1 execute gank"
        def gankOrder = orderFactory.createGankOrder(player1)
        gankOrder.exclusion()

        then:"player1 not attacked"
        0 * player1.hero.attack(player2.hero)
    }

    def "player1 gank player2 when player2 is dead"(){
        setup:
        player1.hero = Mock(Hero)
        player2.hero.death = true

        when:"player1 execute gank"
        def gankOrder = orderFactory.createGankOrder(player1)
        gankOrder.exclusion()

        then:"player2 not attacked"
        0 * player1.hero.attack(player2.hero)
    }

    def "player1 execute push"(){

    }

    def "player1 push the middle tower"(){

    }

    def "player1 push when player2 is defending"(){

    }

    def "player1 execute goback"(){

    }

    def "player1 execute defend"(){

    }

    def "player1 execute magic"(){
        
    }

//    push
//    goback
//    defend
//    magic

}
