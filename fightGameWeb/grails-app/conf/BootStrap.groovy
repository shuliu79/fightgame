import com.liushu.game.fight.Hero
import com.liushu.game.fight.Player
import com.liushu.game.fight.RandomService

class BootStrap {

    def playerService

    def init = { servletContext ->
        buildTestData()
    }
    def destroy = {
    }

    def buildTestData(){


//        100.times {
//            def player = new Player()
//            player.username = "system_build_player$it"
//            player.name = player.username
//            player.securityKey = playerService.generateNewPlayerSecurityKey(player.username)
//            def point = 40;
//            switch (it){
//                case 0..20:point = 25;break
//                case 21..50:point = 30;break
//                case 51..75:point = 35;break
//            }
//            player.save()
//            def index = it
//            3.times {
//                def hero = new Hero()
//                hero.name = "system_build_hero${index}_${it}"
//                hero.power = RandomService.random.nextInt(point-15)+5
//                hero.strength = point - hero.power
//                hero.player = player
//                hero.save()
//            }
//        }
    }

}
