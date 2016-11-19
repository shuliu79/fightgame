package com.liushu.game.fight.test.base.listener

import com.liushu.game.fight.core.model.event.DeathEvent
import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.listener.base.AbstractDeathListener
import org.junit.Test

/**
 * Created by asus-pc on 2016-9-22.
 */
class TestDeathListener extends AbstractDeathListener{

    @Override
    def doHandle(DeathEvent deathEvent) {
        println "in test death listener"
    }

    @Test
    public void test(){
        def listener = new TestDeathListener()
        def deathEvent = new DeathEvent()
        def hurtEvent = new HurtEvent()
        println listener.canHandle(deathEvent)
        println listener.canHandle(hurtEvent)
        println listener.eventClazz
    }

}
