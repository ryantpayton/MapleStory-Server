package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.world.World;
import server.TimerManager;

public class ShutdownCommand extends Command {

    {
        setName("shutdown");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !shutdown [<time>|NOW]");
            return;
        }

        int time = 60000;

        if (params[0].equalsIgnoreCase("now")) {
            time = 1;
        } else {
            time *= Integer.parseInt(params[0]);
        }

        if (time > 1) {
            int seconds = (int) (time / 1000) % 60;
            int minutes = (int) ((time / (1000 * 60)) % 60);
            int hours = (int) ((time / (1000 * 60 * 60)) % 24);
            int days = (int) ((time / (1000 * 60 * 60 * 24)));

            String strTime = "";

            if (days > 0) {
                strTime += days + " days, ";
            }

            if (hours > 0) {
                strTime += hours + " hours, ";
            }

            strTime += minutes + " minutes, ";
            strTime += seconds + " seconds";

            for (World w : Server.getInstance().getWorlds()) {
                for (MapleCharacter chr : w.getPlayerStorage().getAllCharacters()) {
                    chr.dropMessage("Server is undergoing maintenance process, and will be shutdown in " + strTime + ". Prepare yourself to quit safely in the mean time.");
                }
            }
        }

        TimerManager.getInstance().schedule(Server.getInstance().shutdown(false), time);
    }
}
