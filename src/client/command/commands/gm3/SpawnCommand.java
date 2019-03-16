package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;

public class SpawnCommand extends Command {

    {
        setName("spawn");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !spawn <mobid> [<mobqty>]");
            return;
        }

        MapleMonster monster = MapleLifeFactory.getMonster(Integer.parseInt(params[0]));

        if (monster == null) {
            return;
        }

        if (params.length == 2) {
            for (int i = 0; i < Integer.parseInt(params[1]); i++) {
                player.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(Integer.parseInt(params[0])), player.getPosition());
            }
        } else {
            player.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(Integer.parseInt(params[0])), player.getPosition());
        }
    }
}
