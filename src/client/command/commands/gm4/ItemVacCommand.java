package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import java.util.Arrays;
import java.util.List;

public class ItemVacCommand extends Command {

    {
        setName("itemvac");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        List<MapleMapObject> list = player.getMap().getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.ITEM));

        for (MapleMapObject item : list) {
            player.pickupItem(item);
        }
    }
}
