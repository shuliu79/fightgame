package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.event.RoundStartEvent
import com.liushu.game.fight.core.model.listener.base.AbstractRoundStartListener

/**
 * Created by asus-pc on 2016-10-6.
 */
class StorageListener extends AbstractRoundStartListener{

    static final hpRegenerationAdd = 0.8
    static final attackAdd = 1.5

    int lastRound = 0

    Hero hero
    @Override
    def doAfterExecute(RoundStartEvent event) {
        hero.hpRegeneration.enhance((calHpRegeneration(event.round)-calHpRegeneration(event.round-1)))
        def attackEnhance = (calAttack(event.round)-calAttack(event.round-1))
        hero.maxAttack.enhance(attackEnhance)
        hero.minAttack.enhance(attackEnhance)
        lastRound = event.round
    }

    def static calHpRegeneration(int round){
        return (int)(round*hpRegenerationAdd)
    }

    def static calAttack(int round){
        return (int)(attackAdd*attackAdd)
    }

}
