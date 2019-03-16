package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;

public class CakeCommand extends Command {

    {
        setName("cake");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleMonster monster = MapleLifeFactory.getMonster(9400606);

        if (params.length == 1) {
            double mobHp = Double.parseDouble(params[0]);
            int newHp = (mobHp <= 0) ? Integer.MAX_VALUE : ((mobHp > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) mobHp);

            monster.getStats().setHp(newHp);
            monster.setStartingHp(newHp);
        }

        player.getMap().spawnMonsterOnGroundBelow(monster, player.getPosition());
    }
}
