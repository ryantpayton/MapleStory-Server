package client.command.commands.gm6;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import server.ThreadManager;

public class ServerRemoveChannelCommand extends Command {

    {
        setName("removechannel");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        final MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Syntax: @removechannel <worldid>");
            return;
        }

        final int worldId = Integer.parseInt(params[0]);

        ThreadManager.getInstance().newTask(new Runnable() {
            @Override
            public void run() {
                if (Server.getInstance().removeChannel(worldId)) {
                    if (player.isLoggedinWorld()) {
                        player.dropMessage(5, "Successfully removed a channel on World " + worldId + ". Current channel count: " + Server.getInstance().getWorld(worldId).getChannelsSize() + ".");
                    }
                } else {
                    if (player.isLoggedinWorld()) {
                        player.dropMessage(5, "Failed to remove last Channel on world " + worldId + ". Check if either that world exists or there are people currently playing there.");
                    }
                }
            }
        });
    }
}
