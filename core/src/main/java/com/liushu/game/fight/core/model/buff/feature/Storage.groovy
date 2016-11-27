package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.StorageListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-5.
 */
class Storage extends Buff<Hero> implements Feature{

    @Override
    def afterRemove() {
        StorageListener listener = getListener(StorageListener)
        holder.hpRegeneration.weaken(listener.calHpRegeneration(listener.lastRound))
        def attackValue = listener.calAttack(listener.lastRound)
        holder.maxAttack.weaken(attackValue)
        holder.minAttack.weaken(attackValue)
    }

}
