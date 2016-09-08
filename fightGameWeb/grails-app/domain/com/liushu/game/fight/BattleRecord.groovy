package com.liushu.game.fight

class BattleRecord {//todo 用NOSql

    int score
    Player player1
    Player player2
    String records

    Date dateCreated

    static constraints = {
        records maxSize: 4000
    }
}
