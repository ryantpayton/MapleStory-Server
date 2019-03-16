package client.command.commands.gm6;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import server.ThreadManager;

public class ServerAddWorldCommand extends Command {

    {
        setName("addworld");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        final MapleCharacter player = c.getPlayer();

        ThreadManager.getInstance().newTask(new Runnable() {
            @Override
            public void run() {
                int wid = Server.getInstance().addWorld();

                if (player.isLoggedinWorld()) {
                    if (wid >= 0) {
                        player.dropMessage(5, "NEW World " + wid + " successfully deployed.");
                    } else {
                        if (wid == -2) {
                            player.dropMessage(5, "Error detected when loading the 'world.ini' file. World creation aborted.");
                        } else {
                            player.dropMessage(5, "NEW World failed to be deployed. Check if needed ports are already in use or maximum world count has been reached.");
                        }
                    }
                }
            }
        });
    }
}
