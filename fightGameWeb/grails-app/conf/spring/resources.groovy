import com.liushu.game.fight.auth.logout.SimpleRestLogoutHandler
import com.liushu.game.fight.auth.provider.SimpleLoginProvider
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler

// Place your Spring DSL code here
beans = {

    simpleLoginProvider(SimpleLoginProvider){
        userDetailsService = ref('userDetailsService')
        passwordEncoder = ref('passwordEncoder')
        userCache = ref('userCache')
        saltSource = ref('saltSource')
        preAuthenticationChecks = ref('preAuthenticationChecks')
        postAuthenticationChecks = ref('postAuthenticationChecks')
        authoritiesMapper = ref('authoritiesMapper')
        userService = ref('userService')
    }


    logoutSuccessHandler(SimpleRestLogoutHandler) {
        redirectStrategy = ref('redirectStrategy')
        defaultTargetUrl = "/" // '/'
        alwaysUseDefaultTargetUrl = false // false
        targetUrlParameter = null // null
        useReferer = false // false
    }

}
