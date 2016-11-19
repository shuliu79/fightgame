package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.AttackVampireListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-3.
 */
class AttackVampire extends Buff implements Feature{

    AttackVampire() {
        addListener(ListenerFactory.createListener(AttackVampireListener))
    }
}
