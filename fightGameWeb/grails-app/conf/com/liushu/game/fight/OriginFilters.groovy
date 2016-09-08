package com.liushu.game.fight

class OriginFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                response.setHeader("Access-Control-Allow-Origin", "*");
            }
            after = { Map model ->
            }
            afterView = { Exception e ->
            }
        }
    }
}
