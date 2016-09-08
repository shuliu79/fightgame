package com.liushu.game.fight

import com.liushu.game.fight.auth.User

class Player {

    String name
//    String username

//    String securityKey

    Date dateCreated

    static belongsTo = [user:User]

    static hasMany = [heroes:Hero]

    static constraints = {

//        username unique: true
//        securityKey unique: true
    }

    def getSimpleInfo(){
        return [
                id:id,
                username:username,
                displayName:name
        ]
    }
}
