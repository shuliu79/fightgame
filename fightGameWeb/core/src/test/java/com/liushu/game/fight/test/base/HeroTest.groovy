package com.liushu.game.fight.test.base

import com.liushu.game.fight.core.model.HeroType
import com.liushu.game.fight.core.model.factory.HeroFactory
import org.junit.Before
import org.junit.Test
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created by asus-pc on 2016-9-14.
 */
class HeroTest {

    HeroFactory heroFactory
//    HeroFactory heroFactory = Holders.applicationContext.getBean("heroFactory")

    @Test
    public void createHero(){
        def hero = heroFactory.createHero(0,"testHero",HeroType.BLOOD,40,25,15)
        println hero
    }

}
