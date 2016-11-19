package com.liushu.game.fight.core.model.event

import com.liushu.game.fight.core.model.LevelModel

/**
 * Created by asus-pc on 2016-9-20.
 */
class ExperienceEvent extends UnitEvent{

    LevelModel source
    int value

    ExperienceEvent(LevelModel source) {
        super(source)
    }

    @Override
    protected void doExecute() {
        source.experience.add(value)
    }

}
