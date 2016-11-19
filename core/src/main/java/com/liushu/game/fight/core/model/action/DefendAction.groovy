package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.buff.BuffFactory
import com.liushu.game.fight.core.model.buff.DefendStatus

/**
 * Created by asus-pc on 2016-10-1.
 */
class DefendAction extends UnitAction{

    final static hpRegeneration = 5
    final static mpRegeneration = 5

    DefendAction(Unit source) {
        super(source)
    }
//额外回复5点生命和5点魔法，获得10点防御
    @Override
    protected void doExecute() {
        source.hp.add(hpRegeneration)
        source.mp.add(mpRegeneration)
        source.buffPool.addBuff(1,BuffFactory.createBuff(DefendStatus))
    }
}
