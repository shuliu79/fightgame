package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.Player

/**
 * Created by asus-pc on 2016-11-23.
 */
public interface PlayerFactory {

    //hero是唯一的外部数据
    Player createPlayer(Hero hero)

}