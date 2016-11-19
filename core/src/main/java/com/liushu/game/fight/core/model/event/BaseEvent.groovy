package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-5.
 */
abstract class BaseEvent implements Event{

    Unit holder
    boolean valid = true
    int status

    void beforeExecute(Unit h1,Unit h2) {
        //action监听器
        status = StaticValues.BEFORE_ACTION
        if (h1!=null) {
            h1.buffPool.publishEvent(this)
        }
        if (h2!=null) {
            h2.buffPool.publishEvent(this)
        }
    }

    void afterExecute(Unit h1,Unit h2) {
        status = StaticValues.AFTER_ACTION
        if (h1!=null) {
            h1.buffPool.publishEvent(this)
        }
        if (h2!=null) {
            h2.buffPool.publishEvent(this)
        }
    }

    void execute(Unit h1,Unit h2){
        beforeExecute(h1,h2)
        if (valid){
            doExecute()
            afterExecute(h1,h2)
        }
    }

    protected abstract void doExecute()

}
