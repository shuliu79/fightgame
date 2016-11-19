package com.liushu.game.fight.core.model.listener

import com.liushu.game.fight.core.model.action.ActionFactory
import com.liushu.game.fight.core.model.action.AttackAction
import com.liushu.game.fight.core.model.listener.base.AbstractAttackListener

/**
 * Created by asus-pc on 2016-11-13.
 */
class BeatBackListener extends AbstractAttackListener{

    @Override
    protected boolean canHandle_(AttackAction event) {
        holder.equals(event.target)
    }

    @Override
    boolean handleIfDead(){
        return false
    }

    @Override
    def doAfterExecute(AttackAction event) {
        def beatBackAction = event.target.attack()
        holder.executeEvent(beatBackAction)
    }

}
