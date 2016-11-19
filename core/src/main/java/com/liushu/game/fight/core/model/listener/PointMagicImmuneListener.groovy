package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.action.MagicAction
import com.liushu.game.fight.core.model.listener.base.AbstractMagicEventListener

/**
 * Created by asus-pc on 2016-11-17.
 */
class PointMagicImmuneListener extends AbstractMagicEventListener{

    @Override
    protected boolean canHandle_(MagicAction event) {
        holder.equals(event.target)
    }

    @Override
    protected void doBeforeExecute(MagicAction event) {
        //免疫魔法
        event.magic.valid = false
    }

}
