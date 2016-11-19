package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-9-10.
 */
class IntHeroProperty extends BaseHeroProperty<Integer>{

    IntHeroProperty(Integer base) {
        super(base)
        addition = 0;
    }

    Integer getValue(){
        Integer result = ((Integer)base*(rational/100f))+addition
        if (result<0){
            return 0
        }
        return result
    }

    Integer getExtraValue(){
        Integer result = getValue()-base
        return result
    }

}
