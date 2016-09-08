package com.liushu.game.fight

import com.liushu.game.fight.core.Battle
import grails.converters.JSON
import grails.transaction.Transactional

class CommonInterfaceController {

    def playerService
    def heroService

    def index(){
    }

    @Transactional
    def generateToken(String playerName){

        if (playerName == null){
            render ([success:false,msg:"playerName should not be none"])
            return
        }

        def player = Player.findByUsername(playerName)
        if (player!=null){
            render ([success:false,msg:"user already exist"])
            return
        }
        player = new Player(username: playerName, name: playerName
                ,securityKey: playerService.generateNewPlayerSecurityKey(playerName))
        player.save()
        def heroes = []
        3.times {
            def hero = new Hero(
                    name: playerName+"_$it",
                    strength: 10,
                    power: 10,
                    player: player
            )
            //todo 验证
            hero.save()
            heroes.add(hero)
        }
        render ([
                player:player.getSimpleInfo(),
                heroes:heroes.collect{it.getSimpleInfo()},
                securityKey: player.securityKey
        ] as JSON)
    }

    def getPlayerInfoBySecurityKey(String key) {
        def player = Player.findBySecurityKey(key)
        playerService.checkPlayer(player)
        render player.simpleInfo as JSON
    }

    def getHeroesInfosByPlayer(Long playerId){
        def player = Player.get(playerId)
        playerService.checkPlayer(player)
        def heroes = Hero.findAllByPlayerAndEnable(player,true)
        render heroes.collect{
            it.getSimpleInfo()
        } as JSON
    }

    @Transactional
    def updateHeroesOfPlayer(Long playerId,Hero[] heroes){
        def player = Player.get(playerId)
        playerService.checkPlayer(player)
        def oldHeroes = Hero.findAllByPlayerAndEnable(player,true)
        heroService.checkHeroPoints(heroes)//todo sync
        oldHeroes.each {oh->
            def h = heroes.find{
                it.id == oh.id
            }
            oh.strength = h.strength
            oh.power = h.power // todo validate
            oh.save()
        }
        render ([
                success:true
        ] as JSON)
    }

    def fight(Long playerId){

        def player = Player.get(playerId)
        playerService.checkPlayer(player)
        def fp1 = playerService.buildFightPlayer(player)
        def fp2 = playerService.buildFightPlayer(playerService.getAnRandomPlayer(playerId))
        def battle = new Battle()
        battle.doBattle(fp1,fp2)
        playerService.saveBattleResult(battle)
        render ([result:battle.result,message:battle.messages] as JSON)
    }

    def getPlayerStatics(Long playerId){
        def player = Player.get(playerId)
        playerService.checkPlayer(player)
        def records = BattleRecord.findAllByPlayer1OrPlayer2(player,player)
        render ([
                successCount:records.count{
                    (it.player1.id == playerId && it.score>0)||(it.player2.id == playerId && it.score<0)
                },
                failCount:records.count{
                    (it.player1.id == playerId && it.score<0)||(it.player2.id == playerId && it.score>0)
                },
                dogfallCount:records.count{
                    it.score == 0
                }
        ] as JSON)
    }


}
