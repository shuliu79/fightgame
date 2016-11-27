package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.battle.order.Order
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.EventFactory

/**
 * Created by asus-pc on 2016-11-5.
 */
//某位玩家
abstract class Player {

    Hero hero
    List<Creature> creatures = new ArrayList<>()

    Team team
    Class currentOrder

    int gold = 0
    int goldPerTurn = 20;

    Hero getOpponentHero(){
        return team.opponentTeam.player.hero
    }

    Tower getCurrentTower(){
        return team.getCurrentTower()
    }

    Tower getOpponentTower(){
        return team.opponentTeam.getCurrentTower()
    }

    def executeEvent(BaseEvent event){
        hero.executeEvent(event)
    }

    def doFarm(){
        def addGoldEvent = EventFactory.createAddGoldEvent(this,getFarmGoldValue())
        def experienceEvent = EventFactory.createExperienceEvent(hero,getFarmExperienceValue())
        executeEvent(addGoldEvent)
        executeEvent(experienceEvent)
    }

//    for test
    def getMaxFarmGoldValue(){
        return hero.maxAttack.value
    }

//    for test
    def getMinFarmGoldValue(){
        return hero.minAttack.value
    }

    def getFarmGoldValue(){
        return hero.getAttackValue()
    }

    //    for test
    def getMaxFarmExperienceValue(){
        return hero.maxAttack.value
    }

//    for test
    def getMinFarmExperienceValue(){
        return hero.minAttack.value
    }

    def getFarmExperienceValue(){
        return hero.getAttackValue()
    }

}
