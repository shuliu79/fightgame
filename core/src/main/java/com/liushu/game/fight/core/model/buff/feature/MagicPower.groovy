package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.MagicPowerListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-6.
 */
@Deprecated
class MagicPower extends Buff implements Feature{

    MagicPower() {
        addListener(ListenerFactory.createListener(MagicPowerListener))
    }

}
