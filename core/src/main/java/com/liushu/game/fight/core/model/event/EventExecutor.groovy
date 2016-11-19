package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.Unit

/**
 * Created by asus-pc on 2016-9-30.
 */
class EventExecutor {

    Unit h1,h2

    public executeEvent(BaseEvent event){
        event.execute(h1,h2)
    }

}
