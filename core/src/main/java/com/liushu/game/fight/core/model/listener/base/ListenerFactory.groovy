package com.liushu.game.fight.core.model.listener.base

/**
 * Created by asus-pc on 2016-10-3.
 */
class ListenerFactory {

    static BaseListener createListener(Class<BaseListener> clazz){
        def listener = clazz.newInstance()
        return listener
    }

}
