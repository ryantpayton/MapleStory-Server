package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import server.events.gm.MapleEvent;
import tools.MaplePacketCreator;

public class StartEventCommand extends Command {

    {
        setName("startevent");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        int players = 50;

        if (params.length > 1) {
            players = Integer.parseInt(params[0]);
        }

        c.getChannelServer().setEvent(new MapleEvent(player.getMapId(), players));
        Server.getInstance().broadcastMessage(c.getWorld(), MaplePacketCreator.earnTitleMessage("[Event] An event has started on " + player.getMap().getMapName() + " and will allow " + players + " players to join. Type @joinevent to participate."));
        Server.getInstance().broadcastMessage(c.getWorld(), MaplePacketCreator.serverNotice(6, "[Event] An event has started on " + player.getMap().getMapName() + " and will allow " + players + " players to join. Type @joinevent to participate."));
    }
}
