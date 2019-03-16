package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import tools.MapleLogger;
import tools.MaplePacketCreator;

public class IgnoreCommand extends Command {

    {
        setName("ignore");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !ignore <ign>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim == null) {
            player.message("Player '" + params[0] + "' could not be found on this world.");
            return;
        }

        boolean monitored_ = MapleLogger.ignored.contains(victim.getName());

        if (monitored_) {
            MapleLogger.ignored.remove(victim.getName());
        } else {
            MapleLogger.ignored.add(victim.getName());
        }

        player.yellowMessage(victim.getName() + " is " + (!monitored_ ? "now being ignored." : "no longer being ignored."));
        String message_ = player.getName() + (!monitored_ ? " has started ignoring " : " has stopped ignoring ") + victim.getName() + ".";
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(5, message_));
    }
}
