package com.liushu.game.fight.auth.provider

import com.liushu.game.fight.UserService
import com.liushu.game.fight.auth.User
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

/**
 * Created by asus-pc on 2016-8-30.
 */
class SimpleLoginProvider extends DaoAuthenticationProvider{

    def log =  LogFactory.getLog(SimpleLoginProvider.class);
    UserService userService

    @Override
    @Transactional
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        def username = authentication.principal
        def password = authentication.credentials
        def user0 = User.findByUsername(username)
        if (user0 == null && password!=null){//createUser
            log.info("create user:$username")
            userService.createNewUser(username,password)
        }
        return super.authenticate(authentication)
    }

}
