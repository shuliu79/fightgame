package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.model.helpers.BattleHelper
import com.liushu.game.fight.core.model.helpers.HeroHelper
import spock.lang.Specification
import spock.lang.Unroll

//import org.testng.annotations.Test
/**
 * Created by asus-pc on 2016-11-22.
 */
@Unroll
class BattleTest extends Specification{

    def heroFactory = FactoryHolder.instance.heroFactory
    def heroHelper = new HeroHelper()
    def battleHelper = new BattleHelper()

    def "test init battle"(){
        setup:"create two hero and a battle between them"
        def hero = heroHelper.createSimpleHero(1)
        def hero2 = heroHelper.createSimpleHero(2)
        def battle = battleHelper.createBattle(hero,hero2)

        expect:"all fields is init"
        battle.players.size()==2
        battle.players[0] == hero.player
        battle.players[1] == hero2.player
        battle.round == 0

        hero.player.opponentHero == hero2
        hero.player.hero == hero
        hero.player.opponentTower == hero2.player.team.outerTower
        hero.player.currentTower == hero.player.team.outerTower
        hero.player.team.mainTower!=null
        hero.player.team.middleTower !=null
        hero.player.team.outerTower != null
        hero.player.team.battle == battle
        hero.player.team.currentTower == hero.player.team.outerTower
        hero.player.team.opponentTeam == hero2.team
        hero.player.team.player == hero.player

        hero2.player.opponentHero == hero
        hero2.player.hero == hero2
        hero2.player.opponentTower == hero.player.team.outerTower
        hero2.player.currentTower == hero2.player.team.outerTower
        hero2.player.team.mainTower!=null
        hero2.player.team.middleTower !=null
        hero2.player.team.outerTower != null
        hero2.player.team.battle == battle
        hero2.player.team.currentTower == hero2.player.team.outerTower
        hero2.player.team.opponentTeam == hero.team
        hero2.player.team.player == hero2.player
    }

}
