package com.liushu.game.fight.core.model

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.SimpleEventPublisher
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-21.
 */
abstract class Battle {

    Player[] players
    int round = 0

    //执行顺序应该是环境、发出队伍，对立队伍，队伍中执行顺序应该是player>tower,player中为hero>creature
    abstract def executeEvent(BaseEvent event, EventPublisher sourceTeam, EventPublisher targetTeam)



}
