package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-9-12.
 */
class PropertyChangeInfo<T> {

    T oldBase
    T oldExtra
//    int oldRational
//    T oldValue
//
    T newBase
    T newExtra
//    int newRational
//    T newValue

    T getBaseChange(){
        return newBase-oldBase
    }

    T getExtraChange(){
        return newExtra-oldExtra
    }

    T getOldValue(){
        return oldExtra+oldBase
    }

    T getNewValue(){
        return newExtra+newBase
    }

    T getValueChange(){
        return newBase+newExtra-oldBase-oldExtra
    }

}
