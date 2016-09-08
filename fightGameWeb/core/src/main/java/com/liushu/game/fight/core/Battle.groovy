package com.liushu.game.fight.core

import com.liushu.game.fight.core.entities.Hero
import com.liushu.game.fight.core.entities.Player
import org.apache.commons.logging.LogFactory

/**
 * Created by asus-pc on 2016-7-19.
 */
class Battle {

    private static final log = LogFactory.getLog(this)

    List messages = new ArrayList<>();
    def result
    Player attacker
    Player defender

    def doBattle(Player player1,Player player2){
        attacker = player1
        defender = player2
        def index = 0;
        def score = 0;
        player1.heroes.each {
            def result = doFight(it.cloneForFight(),player2.heroes[index].cloneForFight())
            messages.add(result)
            score+=result.score
            index++
        }
        result = [score:score]
        return result
    }

    def doFight = {
        def checkFinish = {Hero hero1,Hero hero2->
            if ((hero1.hp<0)||(hero2.hp<0)){
                return true
            }
        }

        def getRecord = {Hero hero1,Hero hero2->
            return [h1:hero1.getInfo(),h2:hero2.getInfo()]
        }
        return {Hero hero1,Hero hero2->
            def record = []
            while (!checkFinish(hero1,hero2)){
                record.add(getRecord(hero1,hero2))
                hero1.hp-=hero2.attack
                hero2.hp-=hero1.attack
            }
            def score = 0
            if (hero1.hp<0){
                score-=1
            }
            if (hero2.hp<0){
                score+=1
            }
            return  [score:score,
                    hp1:hero1.hp,hp2:hero2.hp,record:record]
//            return result
        }
    }()

}
