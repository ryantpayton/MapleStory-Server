package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.channel.Channel;

public class ReloadEventsCommand extends Command {

    {
        setName("reloadevents");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (Channel ch : Server.getInstance().getAllChannels()) {
            ch.reloadEventScriptManager();
        }

        player.dropMessage(5, "Reloaded Events");
    }
}
