package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleLifeFactory;

public class ZakumCommand extends Command {

    {
        setName("zakum");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.getMap().spawnFakeMonsterOnGroundBelow(MapleLifeFactory.getMonster(8800000), player.getPosition());

        for (int x = 8800003; x < 8800011; x++) {
            player.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(x), player.getPosition());
        }
    }
}
