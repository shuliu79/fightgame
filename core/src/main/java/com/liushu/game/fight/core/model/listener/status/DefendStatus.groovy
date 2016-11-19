package com.liushu.game.fight.core.model.listener.status

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.action.AttackAction
import com.liushu.game.fight.core.model.event.UnitRelation
import com.liushu.game.fight.core.model.listener.base.AbstractAttackListener
import com.liushu.game.fight.core.model.listener.base.UnitMatch

/**
 * Created by asus-pc on 2016-10-1.
 */
@Deprecated //用afterAdd和afterRemove代替效果
class DefendStatus extends AbstractAttackListener implements UnitMatch{

    final static defendValue = 10

    @Override
    def doHandle(AttackAction attackAction) {
        attackAction.addition+= defendValue
    }

    @Override
    boolean canHandle(UnitRelation event, Unit unit) {
        return event.target.id.equals(unit.id)
    }
}
