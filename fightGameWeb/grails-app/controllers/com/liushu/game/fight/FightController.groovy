package com.liushu.game.fight

import com.liushu.game.fight.core.Battle
import grails.converters.JSON

class FightController {

    def springSecurityService
    def playerService

    def findFight() {

        def user = springSecurityService.currentUser
        def player = playerService.getPlayerByUser(user)
        def fp1 = playerService.buildFightPlayer(player)
        def fp2 = playerService.buildFightPlayer(playerService.getAnRandomPlayer(player.id))
        def battle = new Battle()
        battle.doBattle(fp1, fp2)
        playerService.saveBattleResult(battle)
        render([result: battle.result, message: battle.messages] as JSON)

    }

}
