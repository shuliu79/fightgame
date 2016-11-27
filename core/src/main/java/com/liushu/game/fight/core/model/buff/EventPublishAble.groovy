package com.liushu.game.fight.core.model.buff

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event

/**
 * Created by asus-pc on 2016-11-26.
 */
interface EventPublishAble {

    boolean publishEvent(Event event);

}
