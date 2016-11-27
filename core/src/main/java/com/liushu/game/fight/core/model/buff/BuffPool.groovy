package com.liushu.game.fight.core.model.buff

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event

/**
 * Created by asus-pc on 2016-10-2.
 */
interface BuffPool extends EventPublishAble{

    def addBuff(int time,Buff buff)

    def removeBuff(Buff buff)

    boolean hasBuff(Class clazz)

    def nextRound()

}
