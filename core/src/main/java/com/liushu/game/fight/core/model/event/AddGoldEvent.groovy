package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-11-22.
 */
class AddGoldEvent extends BaseEvent{

    Player player
    int value

    AddGoldEvent(Player player) {
        this.player = player
    }

    @Override
    protected void doExecute() {
        if (value>0){
           player.gold += value
        }
    }
}
