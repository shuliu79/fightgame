package com.liushu.game.fight.communicate

import com.google.gson.Gson
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

/**
 * Created by asus-pc on 2016-8-30.
 */
class WebClient {

//    def username
//    def password

    private Gson gson = new Gson()
    def visitor
    def baseUrl = "http://localhost:8080/fightGameWeb"


    def postJson(String url,target){
        visitor(){RestTemplate restTemplate->
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(target), headers);
            restTemplate.postForEntity(url,target,Object.class)
        }
    }

    WebClient(String username,String password){
        visitor = WebUtils.generateVisitor([
                baseUrl: baseUrl,
                username: username,
                password: password
        ])
    }

    def logout(){
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/logout"
            restTemplate.postForEntity(url,null,String.class)
        }
    }

    def resetPassword(String password){
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/account/resetPassword?password=${password}"
            restTemplate.postForEntity(url,null,Map.class)
        }
    }

    def getUserInfo() {
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/hero/getHeroInfo"
            restTemplate.getForEntity(url,Map.class)
        }
    }

    def updateHero(hero){
        def url = baseUrl+"/hero/updateHero"
        postJson(url,hero)
    }

    def findFight(){
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/fight/findFight"
            restTemplate.postForEntity(url,null,Map.class)
        }
    }

    def getBattleTotalStatistics(){
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/statistics/getBattleTotalStatistics"
            restTemplate.getForEntity(url,Map.class)
        }
    }

    def getBattleStatistics(int offset,int max){
        visitor(){RestTemplate restTemplate->
            def url = baseUrl+"/statistics/getBattleStatistics?max=$max&offset=$offset"
            restTemplate.getForEntity(url,List.class)
        }
    }


}
