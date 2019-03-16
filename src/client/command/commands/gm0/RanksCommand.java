package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import tools.MaplePacketCreator;

import java.util.List;
import tools.Pair;

public class RanksCommand extends Command {

    {
        setName("ranks");
        setDescription("See current player ranks.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        List<Pair<String, Integer>> worldRanking = Server.getInstance().getWorldPlayerRanking(player.getWorld());
        player.announce(MaplePacketCreator.showPlayerRanks(9010000, worldRanking));
    }
}
