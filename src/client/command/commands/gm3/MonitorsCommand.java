package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MapleLogger;

public class MonitorsCommand extends Command {

    {
        setName("monitors");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (String ign : MapleLogger.monitored) {
            player.yellowMessage(ign + " is being monitored.");
        }
    }
}
