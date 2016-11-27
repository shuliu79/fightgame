package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.PlayerImpl
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.event.SimpleEventPublisher
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-11-22.
 */
class PlayerFactoryImpl implements PlayerFactory{

    List<Listener> defaultPlayerListeners
    EventPublisherFactory factory

    def initTeamListeners(EventPublisher publisher){
        if (defaultPlayerListeners!=null){
            defaultPlayerListeners.each {
                publisher.registerListener(it)
            }
        }
    }

    @Override
    Player createPlayer(Hero hero) {
        def player = new PlayerImpl()
        player.hero = hero
        hero.player = player
        player.publisher = factory.createPublisher(hero)
        initTeamListeners(player)
        return player
    }
}
