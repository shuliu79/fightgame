package com.liushu.game.fight

import com.liushu.game.fight.errors.EntityNotFoundException
import com.liushu.game.fight.errors.IllegalDataException
import grails.transaction.Transactional

@Transactional
class HeroService {

    final static PLAYER_TOTAL_SKILL_POINT = 80;

    def playerService

    def createNewHero(String name,Player player){
        def hero = new Hero()
        hero.name = name
        hero.power = PLAYER_TOTAL_SKILL_POINT/2;
        hero.strength = PLAYER_TOTAL_SKILL_POINT-hero.power
        hero.player = player
        hero.save()
    }

    def checkHeroesPoint(List heroes) {
        def total = heroes.inject (0){sum,it->
            sum+=it.power+it.strength
        }
        if (total>PLAYER_TOTAL_SKILL_POINT){
            throw new IllegalDataException("total point large than $PLAYER_TOTAL_SKILL_POINT")
        }
    }

    def checkHeroPoint(hero) {
        checkHeroesPoint([hero])
    }

    def checkHeroExist(Hero hero){
        if (hero == null){
            throw new EntityNotFoundException("hero not found")
        }
    }

    def getCurrentHero(){
        def player = playerService.getCurrentPlayer()
        def hero = Hero.findByPlayer(player)
        return hero
    }


}
