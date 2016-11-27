package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Tower

/**
 * Created by asus-pc on 2016-11-23.
 */
public interface TowerFactory {

    Tower createMainTower()

    Tower createMiddleTower()

    Tower createOuterTower()

}