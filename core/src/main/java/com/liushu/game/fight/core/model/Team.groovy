package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher

/**
 * Created by asus-pc on 2016-11-5.
 */
//团队
abstract class Team {

    Player player
    Team opponentTeam

    Tower mainTower
    Tower outerTower
    Tower middleTower

    Battle battle

    def getCurrentTower(){
        if (!outerTower.death){
            return outerTower
        }
        if (!middleTower.death){
            return middleTower
        }
        if (!mainTower.death){
            return mainTower
        }
    }

}
