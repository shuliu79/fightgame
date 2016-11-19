package com.liushu.game.fight.core.battle

import com.justonetech.bimgeom.ifcgeomserver.tools.event.EventPublisher
import com.liushu.game.fight.core.battle.order.Order
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.event.EventFactory
import org.apache.commons.logging.LogFactory

/**
 * Created by asus-pc on 2016-10-9.
 */
class Fight {

    private static final log = LogFactory.getLog(this)

    List messages = new ArrayList<>();
    def result
    Hero hero1
    Hero hero2

    EventPublisher publisher

    def static checkFinish(Hero hero1, Hero hero2) {
        if ((hero1.death) || (hero2.death)) {
            return true
        }
    }

    def static getRecord(Hero hero1, Hero hero2) {
        return [h1: hero1.getInfo(), h2: hero2.getInfo()]
    }

    Order getOrder(Hero hero) {
        //todo
    }

    int getExclusionOrder(Order order,Order order2){
        //todo 根据人物的速度，按照一定概率来决定谁先手
    }

    def exclusionOrder(Order order){
        def orderEvent = EventFactory.createReceiverOrderEvent(order)
        publisher.publishEvent(orderEvent)
        if (order.valid) {
            order.exclusion()
        }
    }

    def doFight(Hero hero1, Hero hero2) {
        def round = 0
        def record = []
        while (!checkFinish(hero1, hero2)) {
            round++
            def roundStartEvent = EventFactory.createRoundStartEvent(round)
            publisher.publishEvent(roundStartEvent)
            record.add(getRecord(hero1, hero2))
            def order1 = getOrder(hero1)
            def order2 = getOrder(hero2)
            if (order1.priority<order2.priority){
                exclusionOrder(order2)
                exclusionOrder(order1)
            }else if(order1.priority>order2.priority){
                exclusionOrder(order1)
                exclusionOrder(order2)
            }else if(getExclusionOrder(order1,order2)){
                exclusionOrder(order1)
                exclusionOrder(order2)
            }else{
                exclusionOrder(order2)
                exclusionOrder(order1)
            }
            def roundFinishEvent = EventFactory.createRoundFinishEvent(round)
            publisher.publishEvent(roundFinishEvent)
        }
    }

}
