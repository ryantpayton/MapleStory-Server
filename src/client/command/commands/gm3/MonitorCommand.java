package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import tools.MapleLogger;
import tools.MaplePacketCreator;

public class MonitorCommand extends Command {

    {
        setName("monitor");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !monitor <ign>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim == null) {
            player.message("Player '" + params[0] + "' could not be found on this world.");
            return;
        }

        boolean monitored = MapleLogger.monitored.contains(victim.getName());

        if (monitored) {
            MapleLogger.monitored.remove(victim.getName());
        } else {
            MapleLogger.monitored.add(victim.getName());
        }

        player.yellowMessage(victim.getName() + " is " + (!monitored ? "now being monitored." : "no longer being monitored."));
        String message = player.getName() + (!monitored ? " has started monitoring " : " has stopped monitoring ") + victim.getName() + ".";
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(5, message));

    }
}
