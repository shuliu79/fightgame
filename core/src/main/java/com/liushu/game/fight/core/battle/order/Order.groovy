package com.liushu.game.fight.core.battle.order

/**
 * Created by asus-pc on 2016-11-13.
 */
import com.liushu.game.fight.core.model.Player

/**
 * Created by asus-pc on 2016-10-9.
 */
interface Order {

    int getPriority()
    def exclusion()
    Player getPlayer()
    boolean isValid()

}