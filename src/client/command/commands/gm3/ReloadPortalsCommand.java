package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import scripting.portal.PortalScriptManager;

public class ReloadPortalsCommand extends Command {

    {
        setName("reloadportals");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        PortalScriptManager.getInstance().reloadPortalScripts();
        player.dropMessage(5, "Reloaded Portals");
    }
}
