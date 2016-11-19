package com.liushu.game.fight.core.model.listener.base

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Unit
import com.liushu.game.fight.core.model.event.UnitRelation

/**
 * Created by asus-pc on 2016-9-29.
 */
@Deprecated
public interface UnitMatch {

    boolean canHandle(UnitRelation event,Unit unit)//通过source，target和eventPublisher的hero判断是否能处理
    //大致有如下几种情况：对方释放，作用在对方身上；作用于我方；我方释放

}