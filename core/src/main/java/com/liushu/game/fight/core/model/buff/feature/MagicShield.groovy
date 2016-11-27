package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.MagicShieldListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-6.
 */
@Deprecated
class MagicShield extends Buff implements Feature{

    MagicShield() {
        addListener(ListenerFactory.createListener(MagicShieldListener))
    }

}
