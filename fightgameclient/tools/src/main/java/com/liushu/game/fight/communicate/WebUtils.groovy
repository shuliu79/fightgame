package com.liushu.game.fight.communicate

import com.justonetech.android.core.restful.RestTemplateFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

/**
 * Created by asus-pc on 2016-8-30.
 */
class WebUtils {

    static {
        RestTemplateFactory.instance.setAjaxEnabled(true)
    }

    def static doRequest(login,action){
        try{
            def result = action.call()
            return result
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                def b = login.call();
                if(b.success) {
                    def result = action.call();
                    return result;
                } else {
                    throw new RuntimeException("登录失败");
                }
            } else {
                throw e;
            }
        }
    }

    def static redirectHoldRequest(action){
        def responseEntity = action.call()
        if (responseEntity.getStatusCode() == HttpStatus.FOUND) {
            String location = responseEntity.getHeaders().get("Location").get(0);
            def newAction = {
//                try {
                    def restTemplate = RestTemplateFactory.instance.getRestTemplate()
//                    ResponseEntity<String> result = restTemplate.getForEntity(location, Object.class);
                    ResponseEntity<String> result = restTemplate.getForEntity(location, Object.class);
                    return result
//                }catch (Exception e){
//                    e.printStackTrace()
//                    return new
//                }
            }
            return redirectHoldRequest(newAction)
        }else{
            return responseEntity.body
        }
    }

    def static login = {baseUrl,username,password->
        String url = baseUrl+ "/j_spring_security_check?j_username={username}&j_password={password}";
        HttpHeaders requestHeaders = new HttpHeaders();
        RestTemplate restTemplate = RestTemplateFactory.getInstance().getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, new HttpEntity<byte[]>(requestHeaders), HashMap.class, username, password);
        if (responseEntity.getStatusCode() == HttpStatus.FOUND) {
            String location = responseEntity.getHeaders().get("Location").get(0);
            def result = restTemplate.getForObject(location, HashMap.class);
            return result;
        } else {
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
        }
        return responseEntity.getBody();
    }


    def static generateVisitor(HashMap config){
        def baseUrl = config.baseUrl
        def username = config.username
        def password = config.password
        def visitorLogin = login.curry(baseUrl,username,password)
        def simpleDoRequest = {action->
            doRequest(visitorLogin){
                redirectHoldRequest(action)
            }
        }
        def restTemplate = RestTemplateFactory.getInstance().getRestTemplate()
        return {Closure<ResponseEntity> action->
            def result = simpleDoRequest(){
                action(restTemplate)
            }
            result
        }
    }


}
