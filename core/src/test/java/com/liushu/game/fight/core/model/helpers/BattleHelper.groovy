package com.liushu.game.fight.core.model.helpers

import com.liushu.game.fight.core.model.FactoryHolder
import com.liushu.game.fight.core.model.Battle
import com.liushu.game.fight.core.model.Hero

/**
 * Created by asus-pc on 2016-11-27.
 */
class BattleHelper {

    def heroHelper = new HeroHelper()

    Battle createBattle(Hero hero1,Hero hero2){
        def player1 = FactoryHolder.instance.playerFactory.createPlayer(hero1)
        def player2 = FactoryHolder.instance.playerFactory.createPlayer(hero2)
        def team1 = FactoryHolder.instance.teamFactory.createTeam(player1)
        def team2 = FactoryHolder.instance.teamFactory.createTeam(player2)
        def battle = FactoryHolder.instance.battleFactory.createBattle(player1,player2)
        team1.opponentTeam = team2
        team2.opponentTeam = team1
        return battle
    }

    Battle createSimpleBattle(){
        def hero1 = heroHelper.createSimpleHero(1)
        def hero2 = heroHelper.createSimpleHero(2)
        return createBattle(hero1,hero2)
    }

}
