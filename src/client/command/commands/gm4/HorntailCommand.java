package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.maps.MapleMap;
import java.awt.*;

public class HorntailCommand extends Command {

    {
        setName("horntail");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        final Point targetPoint = player.getPosition();
        final MapleMap targetMap = player.getMap();

        targetMap.spawnHorntailOnGroundBelow(targetPoint);
    }
}
