package com.liushu.game.fight.core.utils

/**
 * Created by asus-pc on 2016-9-20.
 */
class RandomUtil {

    static Random random

    static int nextInt(Integer a = Integer.MAX_VALUE,Integer b = null){
        if (random==null){
            random = new Random(System.currentTimeMillis())
        }
        if (b==null){
            b = a
            a = 0
        }
        return random.nextInt((b-a))+a
    }

}
