package com.liushu.game.fight

class Hero {

    String name

    Date dateCreated
    Date lastUpdated

    Integer power = 0//力量
    Integer strength = 0//体力

//    Integer hp
//    Integer attack
    Boolean enable = true

    static belongsTo = [player:Player]

    static constraints = {
    }

    def getSimpleInfo = {
        [
                id:id,
                name:name,
                power:power,
                strength:strength
        ]
    }
}
