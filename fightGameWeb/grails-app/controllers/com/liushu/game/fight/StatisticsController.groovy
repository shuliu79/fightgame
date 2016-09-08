package com.liushu.game.fight

import com.liushu.game.fight.core.Battle
import grails.converters.JSON

class StatisticsController {

    def playerService
    def battleService

    def getBattleTotalStatistics() {
        def player = playerService.currentPlayer
        def playerId = player.id
//        def records = BattleRecord.findAllByPlayer1OrPlayer2(player, player)
        def records = battleService.findUserRelationBattle(player)
        render([
                winCount: records.count {
                    it.score > 0
                },
                failCount   : records.count {
                    it.score < 0
                },
                dogfallCount: records.count {
                    it.score == 0
                }
        ] as JSON)
    }

    def getBattleStatistics(Integer max){

        def player = playerService.getCurrentPlayer()
        params.max = Math.min(max ?: 10,100)
        params.sort = "dateCreated"
        params.order = "desc"
        def records = battleService.findUserRelationBattle(player,params)
        render (
                records.collect{[
                    id:it.id,
                    result:it.score,
                    info:it.records
                ]} as JSON
        )

    }


}
