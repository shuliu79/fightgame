package com.liushu.game.fight.core.model.listener.base

import com.liushu.game.fight.core.model.event.ReceiveOrderEvent
import com.liushu.game.fight.core.model.event.RoundStartEvent

/**
 * Created by asus-pc on 2016-10-5.
 */
abstract class AbstractRoundStartListener extends BaseListener<RoundStartEvent>{


    Class eventClazz = ReceiveOrderEvent

}
