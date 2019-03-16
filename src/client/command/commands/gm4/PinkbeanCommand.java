package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleLifeFactory;

public class PinkbeanCommand extends Command {

    {
        setName("pinkbean");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8820001), player.getPosition());
    }
}
