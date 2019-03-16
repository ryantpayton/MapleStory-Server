package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import java.util.List;

public class WarpSnowBallCommand extends Command {

    {
        setName("warpsnowball");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        List<MapleCharacter> chars = player.getMap().getAllPlayers();

        for (MapleCharacter chr : chars) {
            chr.saveLocationOnWarp();
            chr.changeMap(109060000, chr.getTeam());
        }
    }
}
