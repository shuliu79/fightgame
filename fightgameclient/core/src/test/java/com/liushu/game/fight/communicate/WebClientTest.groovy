package com.liushu.game.fight.communicate

import com.justonetech.android.core.restful.RestTemplateFactory
import org.junit.Test
import org.springframework.web.client.RestTemplate

/**
 * Created by asus-pc on 2016-8-31.
 */
class WebClientTest {

    def username = "test"
    def password = "1234"
    def webClient = new WebClient(username,password)

    @Test
    public void resetPassword(){
        println webClient.resetPassword("1234")
    }

    @Test
    public void getUserInfo(){
        println webClient.getUserInfo()
    }

    @Test
    public void updateHero(){
        def hero = [
                power:30,
                strength:50
        ]
        println webClient.updateHero(hero)
    }

    @Test
    public void updateHeroFailed(){
        def hero = [
                power:40,
                strength:50
        ]
        println webClient.updateHero(hero)
    }

    @Test
    public void findFight(){
        println webClient.findFight()
    }

    @Test
    public void getBattleTotalStatistics(){
        println webClient.getBattleTotalStatistics()
    }

    @Test
    public void getBattleStatistics(){
        println webClient.getBattleStatistics(0,5)
    }

    @Test
    public void testLogout(){
        println webClient.getUserInfo()
        println webClient.logout()
        println webClient.getUserInfo()
    }

    @Test
    public void testCreateAndFight(){
        def client0 = new WebClient("test2","1234")
        println client0.getUserInfo()
        println client0.logout()
        println webClient.updateHero([power: 20,strength: 60])
        println webClient.findFight()
        println webClient.getBattleTotalStatistics()
        println webClient.getBattleStatistics(0,5)
    }


}
