package com.liushu.game.fight.test.base

import com.liushu.game.fight.core.Holders
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroType
import com.liushu.game.fight.core.model.action.ActionFactory
import com.liushu.game.fight.core.model.factory.HeroFactoryImpl
import org.junit.Before

//import org.testng.annotations.BeforeClass
//import org.testng.annotations.Test
import org.junit.Test


/**
 * Created by asus-pc on 2016-10-3.
 */
class ActionTest {

    HeroFactoryImpl heroFactory = Holders.applicationContext.getBean("heroFactory")
    Hero h1 = heroFactory.createHero(0,"testHero1",HeroType.BLANK,40,25,15)
    Hero h2 = heroFactory.createHero(0,"testHero2",HeroType.BLANK,40,25,15)

//    @BeforeClass
    @Before
    public void before(){
        heroFactory.eventExecutor.h1 = h1
        heroFactory.eventExecutor.h1 = h1
    }

    @Test
    public void testAttack(){
        def action = ActionFactory.createAttackAction(h1,h2)
        println "hp:"+h2.hp
        h1.eventExecutor.executeEvent(action)
        println "hp:"+h2.hp
    }

    @Test
    public void testDefend(){
        def action = ActionFactory.createDefendAction(h1)
        h1.eventExecutor.executeEvent(action)
        println h1.getInfo()
    }

}
