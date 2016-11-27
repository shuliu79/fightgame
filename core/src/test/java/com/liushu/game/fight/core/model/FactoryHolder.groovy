package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.Holders
import com.liushu.game.fight.core.battle.order.OrderFactory
import com.liushu.game.fight.core.model.factory.BattleFactory
import com.liushu.game.fight.core.model.factory.HeroFactory
import com.liushu.game.fight.core.model.factory.HeroPropertyFactory
import com.liushu.game.fight.core.model.factory.PlayerFactory
import com.liushu.game.fight.core.model.factory.TeamFactory

/**
 * Created by asus-pc on 2016-11-27.
 */
class FactoryHolder {

    static FactoryHolder holder

    static FactoryHolder getInstance(){
        if (holder==null){
           holder = new FactoryHolder()
        }
        return holder
    }

    HeroFactory heroFactory = Holders.applicationContext.getBean("heroFactory")
    HeroPropertyFactory propertyFactory = Holders.applicationContext.getBean("heroPropertyFactory")
    BattleFactory battleFactory = Holders.applicationContext.getBean("battleFactory")
    PlayerFactory playerFactory = Holders.applicationContext.getBean("playerFactory")
    TeamFactory teamFactory = Holders.applicationContext.getBean("teamFactory")
    OrderFactory orderFactory = Holders.applicationContext.getBean("orderFactory")

}
