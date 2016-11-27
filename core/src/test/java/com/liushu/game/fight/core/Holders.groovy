package com.liushu.game.fight.core

import com.liushu.game.fight.core.model.factory.BattleFactory
import com.liushu.game.fight.core.model.factory.HeroFactory
import com.liushu.game.fight.core.model.factory.HeroPropertyFactory
import com.liushu.game.fight.core.model.factory.PlayerFactory
import com.liushu.game.fight.core.model.factory.TeamFactory
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created by asus-pc on 2016-9-15.
 */
class Holders {

    def static final applicationContext =
            new ClassPathXmlApplicationContext(["applicationContext.xml","listeners-and-buffs-beans.xml","model-factory-beans.xml"] as String[])

}
