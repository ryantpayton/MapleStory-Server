package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class OpenPortalCommand extends Command {

    {
        setName("openportal");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !openportal <portalid>");
            return;
        }

        player.getMap().getPortal(params[0]).setPortalState(true);
    }
}
