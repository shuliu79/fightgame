package com.liushu.game.fight.core.system.level

/**
 * Created by asus-pc on 2016-9-11.
 */
class LevelConfig {

    static final int maxLevel = 16;
    static final int[] levelExperience = [0,0,//达到某一级需要的经验值
            100,150,200,250,300,//2~6
            350,400,450,500,550,//7~11
            600,650,700,750,800//12~16
    ] as int[]

    static final int maxExperience = levelExperience.inject(0){a,b->b}

}
