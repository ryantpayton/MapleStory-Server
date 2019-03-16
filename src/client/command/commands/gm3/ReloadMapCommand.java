package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.maps.MapleMap;

import java.util.Collection;

public class ReloadMapCommand extends Command {

    {
        setName("reloadmap");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleMap newMap = c.getChannelServer().getMapFactory().resetMap(player.getMapId());
        int callerid = c.getPlayer().getId();

        Collection<MapleCharacter> characters = player.getMap().getAllPlayers();

        for (MapleCharacter chr : characters) {
            chr.saveLocationOnWarp();
            chr.changeMap(newMap);

            if (chr.getId() != callerid) {
                chr.dropMessage("You have been relocated due to map reloading. Sorry for the inconvenience.");
            }
        }

        newMap.respawn();
    }
}
