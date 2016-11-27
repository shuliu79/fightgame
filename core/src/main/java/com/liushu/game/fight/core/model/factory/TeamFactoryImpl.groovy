package com.liushu.game.fight.core.model.factory

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.justonetech.bimgeom.ifcgeomserver.tools.event.Listener
import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.Team
import com.liushu.game.fight.core.model.TeamImpl

/**
 * Created by asus-pc on 2016-11-23.
 */
class TeamFactoryImpl implements TeamFactory{

    TowerFactory towerFactory
    List<Listener> defaultTeamListeners
    EventPublisherFactory publisherFactory

    protected def initTeamListeners(EventPublisher publisher){
        if (defaultTeamListeners!=null){
            defaultTeamListeners.each {
                publisher.registerListener(it)
            }
        }
    }

    Team createTeam(Player player){
        def team = new TeamImpl()
        player.team = team
        player.hero.team = team
        team.player = player
        team.mainTower = towerFactory.createMainTower()
        team.mainTower.team = team
        team.middleTower = towerFactory.createMiddleTower()
        team.middleTower.team = team
        team.outerTower = towerFactory.createOuterTower()
        team.outerTower.team = team
        team.publisher = publisherFactory.createPublisher(team)
        initTeamListeners(team)
        return team
    }

}
