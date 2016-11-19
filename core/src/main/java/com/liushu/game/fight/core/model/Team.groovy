package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-11-5.
 */
//团队
class Team {

    Player player
    Team opponentTeam

    Tower mainTower
    Tower outerTower
    Tower middleTower

    def getCurrentTower(){
        if (!mainTower.death){
            return mainTower
        }
        if (!outerTower.death){
            return outerTower
        }
        if (!middleTower.death){
            return middleTower
        }
    }

}
