package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-11-5.
 */
//某位玩家
class Player {

    Hero hero
    Team team
    List<Creature> creatures = new ArrayList<>()

    Hero getOpponentHero(){
        return team.opponentTeam.player.hero
    }

    Tower getCurrentTower(){
        return team.getCurrentTower()
    }

    Tower getOpponentTower(){
        return team.opponentTeam.getCurrentTower()
    }

}
