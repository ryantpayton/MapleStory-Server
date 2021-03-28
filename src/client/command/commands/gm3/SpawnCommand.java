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

        if (params.length < 1 || params.length > 2) {
            player.yellowMessage("Syntax: !spawn <mobid> [<mobqty>]");
            return;
        }

        int monsterId = 0;
        int spawnCount = 1;
        MapleMonster monster = null;

        try {
            monsterId = Integer.parseInt(params[0]);
        } catch (NumberFormatException nfe) {
            player.yellowMessage("<mobid> must be an integer.");
            return;
        }

        if (params.length == 2) {
            try {
                spawnCount = Integer.parseInt(params[1]);
            } catch (NumberFormatException nfe) {
                player.yellowMessage("<mobqty> must be an integer.");
                return;
            }
        }

        monster = MapleLifeFactory.getMonster(monsterId);

        if (monster == null) {
            player.yellowMessage("mobid <" + monsterId + "> is invalid.");
            return;
        }

        for (int i = 0; i < spawnCount; i++) {
            player.getMap().spawnMonsterOnGroundBelow(monster, player.getPosition());
        }
    }
}
