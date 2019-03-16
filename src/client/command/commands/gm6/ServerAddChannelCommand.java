package client.command.commands.gm6;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import server.ThreadManager;

public class ServerAddChannelCommand extends Command {

    {
        setName("addchannel");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        final MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Syntax: @addchannel <worldid>");
            return;
        }

        final int worldid = Integer.parseInt(params[0]);

        ThreadManager.getInstance().newTask(new Runnable() {
            @Override
            public void run() {
                int chid = Server.getInstance().addChannel(worldid);

                if (player.isLoggedinWorld()) {
                    if (chid >= 0) {
                        player.dropMessage(5, "NEW Channel " + chid + " successfully deployed on world " + worldid + ".");
                    } else {
                        if (chid == -3) {
                            player.dropMessage(5, "Invalid worldid detected. Channel creation aborted.");
                        } else if (chid == -2) {
                            player.dropMessage(5, "Reached channel limit on worldid " + worldid + ". Channel creation aborted.");
                        } else if (chid == -1) {
                            player.dropMessage(5, "Error detected when loading the 'world.ini' file. Channel creation aborted.");
                        } else {
                            player.dropMessage(5, "NEW Channel failed to be deployed. Check if the needed port is already in use or other limitations are taking place.");
                        }
                    }
                }
            }
        });
    }
}
