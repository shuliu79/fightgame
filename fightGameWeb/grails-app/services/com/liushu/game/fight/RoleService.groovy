package com.liushu.game.fight

import com.liushu.game.fight.auth.Role
import grails.transaction.Transactional

@Transactional
class RoleService {

    private final static USER_ROLE = "USER_ROLE"
    private final static ADMIN_ROLE = "ADMIN_ROLE"

    def getOrCreateUserRole() {
        def userRole = Role.findByAuthority(USER_ROLE)
        if (userRole==null){
            userRole = new Role(authority: USER_ROLE)
            userRole.save(flush: true)
        }
        return userRole
    }

    def getOrCreateAdminRole(){
        def adminRole = Role.findByAuthority(ADMIN_ROLE)
        if (adminRole==null){
            adminRole = new Role(authority: ADMIN_ROLE)
            adminRole.save(flush: true)
        }
        return adminRole
    }

}
