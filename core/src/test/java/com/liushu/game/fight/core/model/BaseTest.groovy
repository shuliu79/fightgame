package com.liushu.game.fight.core.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by asus-pc on 2016-11-27.
 */
@Unroll
class BaseTest extends Specification{

    def "test then"(){

        setup:
        def hero = Mock(Hero)

        when:
        hero.defend()

        then:
        1 * hero.defend()

    }

}
