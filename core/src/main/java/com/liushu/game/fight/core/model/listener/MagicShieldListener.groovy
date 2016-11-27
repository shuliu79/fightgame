package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.event.HurtEvent
import com.liushu.game.fight.core.model.listener.base.AbstractHurtListener

/**
 * Created by asus-pc on 2016-10-6.
 */
class MagicShieldListener extends AbstractHurtListener{

    def factor = 2

    @Override
    void doBeforeExecute(HurtEvent event) {
        if (event.target.mp.value>event.value){
            reduceHurt(event,event.value)
        }else{
            reduceHurt(event,event.target.mp.value)
        }
    }

    def reduceHurt(HurtEvent event,int magicValue){
        event.target.mp.weaken(magicValue)
        event.value -= (int)(magicValue/factor)
        if (magicValue % factor>0){
            event.value-=1
        }
    }

    @Override
    protected boolean canHandle_(HurtEvent event) {
        holder.equals(event.target)
    }
}
