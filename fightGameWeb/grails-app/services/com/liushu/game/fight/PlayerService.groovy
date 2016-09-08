package com.liushu.game.fight

import com.liushu.game.fight.auth.User
import com.liushu.game.fight.core.Battle
import com.liushu.game.fight.errors.EntityNotFoundException
import com.liushu.game.fight.errors.SystemException
import grails.converters.JSON
import grails.transaction.Transactional
import com.liushu.game.fight.core.entities.Player as FightPlayer
import com.liushu.game.fight.core.entities.Hero as FightHero

@Transactional
class PlayerService {

    def heroService
    def springSecurityService

    def createNewPlayer(String name,User user){
        def player = new Player()
        player.name = name
        player.user = user
        player.save()
        def hero = heroService.createNewHero(name,player)
        player.addToHeroes(hero)
    }

    def checkPlayerExist(Player player){
        if (player == null){
            throw new EntityNotFoundException("player not found")
        }
    }

    def getPlayerByUser(User user){
        def player = Player.findByName(user.username)
        return player
    }

    def getCurrentPlayer(){
        def user = springSecurityService.currentUser
        def player = getPlayerByUser(user)
        return player
    }

    def buildFightPlayer(Player player){
        def playerId = player.id
        def fightPlayer = new FightPlayer()
        fightPlayer.id = playerId
        def heroes = Hero.findAllByPlayerAndEnable(player,true)
        def fightHeroes = heroes.collect{
            def fightHero = new FightHero()
            fightHero.id = it.id
            fightHero.name = it.name
            fightHero.attack = it.power*Config.powerPoint
            fightHero.hp = it.strength*Config.strengthPoint
            return fightHero
        }
        fightPlayer.heroes = fightHeroes.toArray(new FightHero[0])
        return fightPlayer
    }

    def getAnRandomPlayer(Long exclusionPlayerId){
        def allPlayer = Player.findAll()
        allPlayer.remove(allPlayer.find{it.id == exclusionPlayerId})
        if (allPlayer.size()==0){
            throw new SystemException("no other user found")
        }
        return allPlayer.get(RandomService.random.nextInt(allPlayer.size()))
    }

    def saveBattleResult(Battle battle){

        def battleRecord = new BattleRecord(
                score:battle.result.score,
                player1: Player.get(battle.attacker.id),
                player2: Player.get(battle.defender.id),
                records:(battle.messages as JSON).toString()
        )
        if (!battleRecord.validate()){
            log.error("save battle record failed:${battleRecord.errors.toString()}")
        }else{
            battleRecord.save()
        }

    }

    def generateNewPlayerSecurityKey(String playerName){
        def generate = {
            def num = RandomService.random.nextLong()
            def key = (num + playerName).encodeAsMD5()
        }
        def result = generate()
        for (;Player.findBySecurityKey(result)!=null;result=generate()){}
        return result
    }

}
