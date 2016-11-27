package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.BeatBackListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-11-13.
 */
@Deprecated
class BeatBack extends Buff implements Feature{

    BeatBack() {
        addListener(ListenerFactory.createListener(BeatBackListener))
    }
}

