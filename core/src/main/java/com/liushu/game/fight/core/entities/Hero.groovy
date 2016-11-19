package com.liushu.game.fight.core.entities

/**
 * Created by asus-pc on 2016-6-19.
 */
@Deprecated
class Hero {

    String id;
    String name;

    //英雄层
    int heroType


    Integer hp;
    Integer attack;


    @Override
    public String toString() {
        return name;
    }

    def Hero cloneForFight(){
        return new Hero(
                id:id,
                name:name,
                hp: hp,
                attack: attack
        )
    }

    def getInfo(){
        return [
                id:id,
                name:name,
                attack:attack,
                hp:hp
        ]
    }
}
