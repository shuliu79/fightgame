package com.liushu.game.fight

import com.liushu.game.fight.auth.User
import com.liushu.game.fight.auth.UserRole
import grails.transaction.Transactional

@Transactional
class UserService {

    def roleService
    def playerService

    def createNewUser(String username,String password) {

        def user = new User()
        user.username = username
        user.password = password
        user.enabled = true
        user.save(flush: true)
        def userRole = roleService.getOrCreateUserRole()
        UserRole.create(user,userRole,true)
        playerService.createNewPlayer(username,user)

    }

}
