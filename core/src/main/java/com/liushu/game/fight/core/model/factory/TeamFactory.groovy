package com.liushu.game.fight.core.model.factory

import com.liushu.game.fight.core.model.Player
import com.liushu.game.fight.core.model.Team

/**
 * Created by asus-pc on 2016-11-23.
 */
interface TeamFactory {

    Team createTeam(Player player)

}
