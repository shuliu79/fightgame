package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-9-10.
 */
class BaseHeroProperty<T> {

    T base
    T addition
    int rational = 100

//    Closure beforeAdd
//    Closure afterAdd
//    Closure beforeSubtract
//    Closure afterSubtract
//    Closure beforeEnhance
//    Closure afterEnhance
//    Closure beforeWeaken
//    Closure afterWeaken
//    Closure beforeRationalAdd
//    Closure afterRationalAdd
//    Closure beforeRationalSubtract
//    Closure afterRationalSubtract

    Closure onBaseAdd
    Closure onBaseSubtract
    Closure onExtraAdd
    Closure onExtraSubtract

    protected onBaseChange(PropertyChangeInfo<T> info){
        if (info.getBaseChange()>0){
            onBaseAdd && onBaseAdd(this,info)
        }else if (info.getBaseChange()<0){
            onBaseSubtract && onBaseSubtract(this,info)
        }
        onExtraChange(info)
        return this
    }

    protected onExtraChange(PropertyChangeInfo<T> info){
        if (info.getExtraChange()>0){
            onExtraAdd && onExtraAdd(this,info)
        }else if (info.getExtraChange()<0){
            onExtraSubtract && onExtraSubtract(this,info)
        }
        return this
    }

    protected addOldValue(PropertyChangeInfo<T> info){
        info.oldBase = (base instanceof Cloneable)?base.clone():base
        info.oldExtra = extraValue
    }


    protected addNewValue(PropertyChangeInfo<T> info){
        info.newBase = (base instanceof Cloneable)?base.clone():base
        info.newExtra = extraValue
    }

    BaseHeroProperty(T base) {
        this.base = base
    }

    protected baseChangeWrap(action){
        def info = new PropertyChangeInfo<T>()
        addOldValue(info)
        action()
        addNewValue(info)
        onBaseChange(info)
    }

    protected extraChangeWrap(action){
        def info = new PropertyChangeInfo<T>()
        addOldValue(info)
        action()
        addNewValue(info)
        onExtraChange(info)
    }

    def add(T num){
        baseChangeWrap() {
            base += num
        }
    }

    T subtract(T num){
        def temp = base
        baseChangeWrap() {
            base -= num
            if (base<0){
                base = 0
            }
        }
        return temp-base
    }

    def enhance(T num){
        extraChangeWrap() {
            addition += num
        }
    }

    def weaken(T num){
        extraChangeWrap(){
            addition-=num
        }
    }

    def rationalAdd(T num){
        extraChangeWrap() {
            rational += num
        }
    }

    def rationalSubtract(T num){
        extraChangeWrap() {
            rational -= num
        }
    }

    T getValue(){
        T result = ((T)base*(rational/100f))+addition
        if (result<0){
            return 0
        }
        return result
    }

    T getExtraValue(){
        T result = getValue()-base
        return result
    }

    String toString(){
        return getValue()
    }

}
