package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;

import java.util.ArrayList;
import java.util.Arrays;

import net.server.Server;

public class UptimeCommand extends Command {

    {
        setName("uptime");
        setDescription("Shows how long the server has been running.");

        setOtherNames(
                new ArrayList<>(Arrays.asList("lastrestart"))
        );
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        long milliseconds = System.currentTimeMillis() - Server.uptime;
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        int days = (int) ((milliseconds / (1000 * 60 * 60 * 24)));

        c.getPlayer().yellowMessage("Server has been online for " + days + " days " + hours + " hours " + minutes + " minutes and " + seconds + " seconds.");
    }
}
