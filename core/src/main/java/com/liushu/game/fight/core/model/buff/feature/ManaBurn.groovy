package com.liushu.game.fight.core.model.buff.feature

import com.liushu.game.fight.core.model.buff.Buff
import com.liushu.game.fight.core.model.listener.ManaBurnListener
import com.liushu.game.fight.core.model.listener.base.ListenerFactory

/**
 * Created by asus-pc on 2016-10-4.
 */
class ManaBurn extends Buff implements Feature{

    static final manaBurnValue = 5

    ManaBurn() {
        ManaBurnListener listener = ListenerFactory.createListener(ManaBurnListener) as ManaBurnListener
        listener.setBurnValue(manaBurnValue)
        addListener(listener)
    }

}
