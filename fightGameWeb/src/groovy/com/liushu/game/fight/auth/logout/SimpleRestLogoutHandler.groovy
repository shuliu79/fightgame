package com.liushu.game.fight.auth.logout

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by asus-pc on 2016-9-3.
 */
class SimpleRestLogoutHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler{

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if(request.getHeader("X-Requested-With").equals("XMLHttpRequest")){
            response.setContentType("application/json")
            response.getWriter().write(
                    "{\"success\":true}"
            )
        }else{
            super.handle(request,response,authentication)
        }
    }

}
