package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Battle
import com.liushu.game.fight.core.model.Player

/**
 * Created by asus-pc on 2016-11-23.
 */
public interface BattleFactory {

    //要创建一个战斗必须要有两位玩家
    Battle createBattle(Player player,Player player2)

}