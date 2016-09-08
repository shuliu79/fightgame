package com.liushu.game.fight

import grails.transaction.Transactional

@Transactional
class BattleService {

    def findUserRelationBattle(Player player,params = null) {

        def records = BattleRecord.findAllByPlayer1OrPlayer2(player,player,params)
        def result = records.collect{
            if (it.player1Id==player.id) {
                [
                        score:it.score,
                        player1:it.player1,
                        player2:it.player2,
                        records:it.records,
                        dateCreated:it.dateCreated
                ]
            }else{
                [
                        score:-it.score,
                        player1:it.player2,
                        player2:it.player1,
                        records:it.records,
                        dateCreated:it.dateCreated
                ]
            }
        }
        return result

    }


}
