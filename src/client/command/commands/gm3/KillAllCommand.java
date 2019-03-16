package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleMonster;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import java.util.Arrays;
import java.util.List;

public class KillAllCommand extends Command {

    {
        setName("killall");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleMap map = player.getMap();
        List<MapleMapObject> monsters = map.getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        int count = 0;

        for (MapleMapObject monstermo : monsters) {
            MapleMonster monster = (MapleMonster) monstermo;

            if (!monster.getStats().isFriendly() && !(monster.getId() >= 8810010 && monster.getId() <= 8810018)) {
                map.damageMonster(player, monster, Integer.MAX_VALUE);
                count++;
            }
        }

        player.dropMessage(5, "Killed " + count + " monsters.");
    }
}
