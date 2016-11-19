package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Team
import com.liushu.game.fight.core.model.Tower

/**
 * Created by asus-pc on 2016-11-13.
 */
class TowerFactory {

    //塔的buffer
    //反击，当被攻击的时候反击
    //魔法抵抗（免疫所有指向性魔法和魔法伤害的作用）
    //魔法看做是event的一种，通过设置event的valid属性来禁用魔法

    protected createTowerFeature(Tower tower) {

    }

    protected createTowerWithConfig(Team team, config) {
        def tower = new Tower()
        tower.armor = config.armor
        tower.maxAttack = config.maxAttack
        tower.minAttack = config.minAttack
        tower.maxHp = config.maxHp
        tower.hp = config.hp
        tower.maxMp = config.maxMp
        tower.mp = config.mp
        tower.team = team
        return tower
    }


    protected outerTowerConfig = [
            armor    : 5,
            maxAttack: 50,
            minAttack: 50,
            maxHp    : 200,
            hp       : 200,
            maxMp    : 0,
            mp       : 0
    ]

    protected middleTowerConfig = [
            armor    : 7,
            maxAttack: 70,
            minAttack: 70,
            maxHp    : 250,
            hp       : 250,
            maxMp    : 0,
            mp       : 0
    ]

    protected mainTowerConfig = [
            armor    : 10,
            maxAttack: 100,
            minAttack: 100,
            maxHp    : 300,
            hp       : 300,
            maxMp    : 0,
            mp       : 0
    ]

    Tower createOuterTower(Team team) {
        return createTowerWithConfig(team,outerTowerConfig)
    }

    Tower createMiddleTower(Team team) {
        return createTowerWithConfig(team,middleTowerConfig)
    }

    Tower createMain(Team team) {
        return createTowerWithConfig(team,mainTowerConfig)
    }

}
