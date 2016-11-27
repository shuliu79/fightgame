package com.liushu.game.fight.core.model

/**
 * Created by asus-pc on 2016-9-7.
 */
class HeroType {

    final static BLANK = new HeroType(
            name:"blank",
            type: 0,
            talent: {}
    )

    final static int POWER_TYPE = 0
    final static int STRENGTH_TYPE = 1
    final static int MIND_TYPE = 2
    final static int BALANCE_TYPE = 3

    String name
    int type
    def talent

    static def getGrowth(HeroType heroType){
        switch (heroType.type){
            case(POWER_TYPE):
                return [
                        power:4,
                        strength:2,
                        mind:2
                        ];
                break;
            case (STRENGTH_TYPE):
                return [
                        power:2,
                        strength: 4,
                        mind: 2
                ]
                break;
            case (MIND_TYPE):
                return [
                        power:2,
                        strength:2,
                        mind:4
                ]
                break;
            case (BALANCE_TYPE):
                return [
                        power: 3,
                        strength: 3,
                        mind: 3
                ]
                break;
        }
    }

    static def getHeroTypeRational(HeroType heroType){
        switch (heroType.type){
            case (POWER_TYPE):
                return [
                        power:10,
                ]
            break;
            case (STRENGTH_TYPE):
                return [
                        strength: 10,
                ]
            break;
            case (MIND_TYPE):
                return [
                        strength: 0,
                ]
            break;
            case (BALANCE_TYPE):
                return [:]
            break;
        }
    }

}
