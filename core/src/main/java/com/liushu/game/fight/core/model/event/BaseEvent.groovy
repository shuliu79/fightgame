package com.liushu.game.fight.core.model.event

import com.justonetech.bimgeom.ifcgeomserver.tools.event.Event
import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.system.StaticValues

/**
 * Created by asus-pc on 2016-10-5.
 */
abstract class BaseEvent implements Event{

    boolean valid = true
    int status

    void execute(){
            doExecute()
    }

    protected abstract void doExecute()

}
