package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-11-5.
 */
public interface LevelModel extends Unit{

    IntHeroProperty getLevel()

    void setLevel(IntHeroProperty level)

    IntHeroProperty getExperience()

    void setExperience(IntHeroProperty experience)

    IntHeroProperty getTotalExperience()

    void setTotalExperience(IntHeroProperty totalExperience)

}