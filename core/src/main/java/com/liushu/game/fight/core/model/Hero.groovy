package com.liushu.game.fight.core.model

import com.liushu.game.fight.core.model.action.ActionFactory
import com.liushu.game.fight.core.model.event.BaseEvent
import com.liushu.game.fight.core.model.event.UnitEvent
import com.liushu.game.fight.core.model.magic.Magic
import com.liushu.game.fight.core.utils.GsonUtil

/**
 * Created by asus-pc on 2016-9-10.
 */
class Hero extends Creature implements LevelModel{

    HeroType type

    IntHeroProperty level
    IntHeroProperty experience
    IntHeroProperty totalExperience

    IntHeroProperty powerGrowth
    IntHeroProperty strengthGrowth
    IntHeroProperty mindGrowth

    IntHeroProperty power
    IntHeroProperty strength
    IntHeroProperty mind

    IntHeroProperty magicPower //魔力

    Player player

    def defend(){
        def defend = ActionFactory.createDefendAction(this)
        executeEvent(defend)
    }

    def userMagic(Magic magic){
        def magicAction = ActionFactory.createMagicAction(this,magic)
        executeEvent(magicAction)
    }

    def getInfo(){
        GsonUtil.gson.toJson([
                id:id,
                name:name,
                type:type?.name,
                level:level.getValue(),
                experience:experience.getValue(),
                totalExperience:totalExperience.getValue(),
                powerGrowth:powerGrowth.getValue(),
                strengthGrowth:strengthGrowth.getValue(),
                mindGrowth:mindGrowth.getValue(),
                power:power.getValue(),
                strength:strength.getValue(),
                mind:mind.getValue(),
                armor:armor.getValue(),
                resistance:resistance.getValue(),
                magicPower:magicPower.getValue(),
                maxAttack:maxAttack.getValue(),
                minAttack:minAttack.getValue(),
                maxHp:maxHp.getValue(),
                hp:hp.getValue(),
                maxMp:maxMp.getValue(),
                mp:mp.getValue(),
                hpRegeneration:hpRegeneration.getValue(),
                mpRegeneration:mpRegeneration.getValue(),
        ])
    }



}
