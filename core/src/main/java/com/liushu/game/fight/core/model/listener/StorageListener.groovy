package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.RoundStartEvent
import com.liushu.game.fight.core.model.listener.base.AbstractRoundStartListener

/**
 * Created by asus-pc on 2016-10-6.
 */
class StorageListener extends AbstractRoundStartListener{

    def hpRegenerationAdd = 0.8
    def attackAdd = 1.5

    int lastRound = 0

    @Override
    void doAfterExecute(RoundStartEvent event) {
        (Unit)holder.hpRegeneration.enhance((calHpRegeneration(event.round)-calHpRegeneration(event.round-1)))
        def attackEnhance = (calAttack(event.round)-calAttack(event.round-1))
        (Unit)holder.maxAttack.enhance(attackEnhance)
        (Unit)holder.minAttack.enhance(attackEnhance)
        lastRound = event.round
    }

    def calHpRegeneration(int round){
        return (int)(round*hpRegenerationAdd)
    }

    def calAttack(int round){
        return (int)(attackAdd*attackAdd)
    }

}
