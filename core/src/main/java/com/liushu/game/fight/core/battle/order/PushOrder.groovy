package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-18.
 */
class PushOrder extends BaseOrder{
    @Override
    int getPriority() {
        return StaticValues.LOW
    }

    @Override
    def exclusion() {
        player.creatures.each {
            it.attack(player.getOpponentTower())
        }
        player.hero.attack(player.getOpponentTower())
    }
}
