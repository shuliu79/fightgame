package com.liushu.game.fight.core.model.listener.base

import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.action.MagicAction

/**
 * Created by asus-pc on 2016-11-15.
 */
abstract class AbstractMagicEventListener extends BaseListener<MagicAction>{

    Class eventClazz = HurtEvent

}
