package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-18.
 */
class GankOrder extends BaseOrder{

    GankOrder(Player player) {
        super(player)
    }

    @Override
    int getPriority() {
        return StaticValues.LOW
    }

    @Override
    def exclusion() {
        super.exclusion()
        if (!player.getOpponentHero().death &&
                !player.team.getOpponentTeam().player.currentOrder.equals(GoBackOrder.class)) {
            player.creatures.each {
                it.attack(player.getOpponentHero())
            }
            player.hero.attack(player.getOpponentHero())
        }
    }

}
