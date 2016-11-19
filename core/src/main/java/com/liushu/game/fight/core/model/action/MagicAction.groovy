package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.action.TargetAction
import com.liushu.game.fight.core.model.magic.Magic

/**
 * Created by asus-pc on 2016-11-15.
 */
class MagicAction extends TargetAction{

    Magic magic

    MagicAction(Unit source, Unit target) {
        super(source, target)
    }

    @Override
    protected void doExecute() {
        if(magic.valid) {
            magic.execute()
        }
    }
}
