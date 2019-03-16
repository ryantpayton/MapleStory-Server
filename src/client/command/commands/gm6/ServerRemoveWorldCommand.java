package client.command.commands.gm6;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import server.ThreadManager;

public class ServerRemoveWorldCommand extends Command {

    {
        setName("removeworld");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        final MapleCharacter player = c.getPlayer();
        final int rwid = Server.getInstance().getWorldsSize() - 1;

        if (rwid <= 0) {
            player.dropMessage(5, "Unable to remove world 0.");
            return;
        }

        ThreadManager.getInstance().newTask(new Runnable() {
            @Override
            public void run() {
                if (Server.getInstance().removeWorld()) {
                    if (player.isLoggedinWorld()) {
                        player.dropMessage(5, "Successfully removed a world. Current world count: " + Server.getInstance().getWorldsSize() + ".");
                    }
                } else {
                    if (player.isLoggedinWorld()) {
                        if (rwid < 0) {
                            player.dropMessage(5, "No registered worlds to remove.");
                        } else {
                            player.dropMessage(5, "Failed to remove world " + rwid + ". Check if there are people currently playing there.");
                        }
                    }
                }
            }
        });
    }
}
