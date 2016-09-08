package com.liushu.game.fight

import grails.converters.JSON

/**
 * Created by asus-pc on 2016-8-31.
 */
class MsgUtils {

    def static successMsg(msg = null){
        if (msg){
            return [
                    success:true,
                    msg:msg
            ] as JSON
        }else{
           return [
                   success:true
           ] as JSON
        }
    }

    def static errorMsg(msg = null){
        if (msg){
            return [
                    success: false,
                    msg:msg
            ] as JSON
        }else{
            return [
                success:false
            ] as JSON
        }

    }

}
