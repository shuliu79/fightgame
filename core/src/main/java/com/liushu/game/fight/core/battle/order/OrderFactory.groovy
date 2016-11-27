package com.liushu.game.fight.core.battle.order

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.magic.Magic

/**
 * Created by asus-pc on 2016-11-27.
 */
class OrderFactory {

    def createFarmOrder(Player player){
        def farmOrder = new FarmOrder(player)
        return farmOrder
    }

    def createDefendOrder(Player player){
        def defendOrder = new DefendOrder(player)
        return defendOrder
    }

    def createGankOrder(Player player){
        return new GankOrder(player)
    }

    def createGoBackOrder(Player player){
        return new GoBackOrder(player)
    }

    def createMagicOrder(Player player,Magic magic){
        return new MagicOrder(player,magic)
    }

    def createPushOrder(Player player){
        return new PushOrder(player)
    }

}
