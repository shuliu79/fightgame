package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.StorageListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-5.
 */
class Storage extends Buff<Hero> implements Feature{

    Storage(Hero hero) {
        StorageListener listener = ListenerFactory.createListener(StorageListener) as StorageListener
        listener.setHero(hero)
        addListener(listener)
    }

    @Override
    def afterRemove(Hero hero) {
        StorageListener listener = getListener(StorageListener)
        hero.hpRegeneration.weaken(StorageListener.calHpRegeneration(listener.lastRound))
        def attackValue = StorageListener.calAttack(listener.lastRound)
        hero.maxAttack.weaken(attackValue)
        hero.minAttack.weaken(attackValue)
    }

}
