package com.liushu.game.fight

import com.liushu.game.fight.auth.User
import grails.converters.JSON
import grails.transaction.Transactional

class HeroController {

    def springSecurityService
    def heroService
    def playerService

    def getHeroInfo() {

        def hero = heroService.getCurrentHero()
        render (
                [
                        id:hero.id,
                        power:hero.power,
                        strength:hero.strength
                ] as JSON
        )

    }

    @Transactional
    def updateHero(){
        def data =  request.JSON
        def heroData = data as HashMap
        def hero = heroService.getCurrentHero()
//        heroService.checkHeroExist(hero)
        heroService.checkHeroPoint(heroData)
        hero.power = heroData.power
        hero.strength = heroData.strength
        hero.save()
        render MsgUtils.successMsg()
    }


}
