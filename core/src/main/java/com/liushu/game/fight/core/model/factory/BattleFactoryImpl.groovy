package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Battle
import com.liushu.game.fight.core.model.BattleImpl
import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.event.SimpleEventPublisher

/**
 * Created by asus-pc on 2016-11-22.
 */
class BattleFactoryImpl implements BattleFactory{

    List<Listener> defaultBattleListeners
    EventPublisherFactory eventPublisherFactory
    EventExecutorFactory eventExecutorFactory

    protected initBattlePublisher(EventPublisher publisher){
        if (defaultBattleListeners!=null) {
            defaultBattleListeners.each {
                publisher.registerListener(it)
            }
        }
    }

    def Battle createBattle(Player player,Player player2){
        def battle = new BattleImpl()
        battle.eventExecutor = eventExecutorFactory.eventExecutor
        battle.publisher = eventPublisherFactory.createPublisher(battle)
        battle.players = [player,player2]
        player.team.battle = battle
        player2.team.battle = battle
        initBattlePublisher(battle)
        return battle
    }

}
