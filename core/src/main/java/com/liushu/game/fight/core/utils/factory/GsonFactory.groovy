package com.liushu.game.fight.core.utils.factory

import com.google.gson.GsonBuilder

/**
 * Created by asus-pc on 2016-9-17.
 */
class GsonFactory {

    def static getGson(){
        def gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        return gson
    }
}
