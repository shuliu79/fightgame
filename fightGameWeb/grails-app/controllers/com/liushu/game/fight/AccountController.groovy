package com.liushu.game.fight

import com.liushu.game.fight.auth.User
import grails.transaction.Transactional

class AccountController {

    def springSecurityService

    @Transactional
    def resetPassword(String password) {
        User user = springSecurityService.currentUser
        user.password = password
        user.save()
        render MsgUtils.successMsg()
    }
}
