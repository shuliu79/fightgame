package com.liushu.game.fight.core.model.action

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.magic.Magic

/**
 * Created by asus-pc on 2016-10-2.
 */
class ActionFactory {

    static AttackAction createAttackAction(Unit source,Unit target){
        def attackAction = new AttackAction(source,target)
        return attackAction
    }

    static DefendAction createDefendAction(Unit unit){
        def defendAction = new DefendAction(unit)
        return defendAction
    }

    static MagicAction createMagicAction(Unit unit,Magic magic){
        def magicAction = new MagicAction(unit,magic.target)
        return magicAction
    }

}
