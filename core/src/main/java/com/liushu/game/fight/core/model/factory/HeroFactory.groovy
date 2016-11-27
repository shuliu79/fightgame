package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Hero
import com.liushu.game.fight.core.model.HeroType

/**
 * Created by asus-pc on 2016-11-23.
 */
public interface HeroFactory {

    //根据外部数据来创建一个可用的hero
    Hero createHero(long id,String name,HeroType type,int power,int strength,int mind)

}