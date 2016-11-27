package com.liushu.game.fight.test.base

import com.liushu.game.fight.core.Holders
import com.liushu.game.fight.core.model.HeroType
import com.liushu.game.fight.core.model.factory.HeroFactoryImpl
import org.junit.Test

//import org.testng.annotations.Test

/**
 * Created by asus-pc on 2016-9-14.
 */
class HeroTest {

//    HeroFactory heroFactory
    HeroFactoryImpl heroFactory = Holders.applicationContext.getBean("heroFactory")

    @Test
    public void createHero(){
        def hero = heroFactory.createHero(0,"testHero",HeroType.BLANK,40,25,15)
        println hero.getInfo()
    }

}
