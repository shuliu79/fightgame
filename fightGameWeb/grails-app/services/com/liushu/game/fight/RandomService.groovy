package com.liushu.game.fight

import grails.transaction.Transactional

@Transactional
class RandomService {

    static Random random = new Random(System.currentTimeMillis())


}
