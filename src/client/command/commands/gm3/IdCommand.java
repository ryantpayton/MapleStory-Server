package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class IdCommand extends Command {

    {
        setName("id");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !id <id>");
            return;
        }

        try {
            try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL("http://www.mapletip.com/search_java.php?search_value=" + params[0] + "&check=true").openConnection().getInputStream()))) {
                String s;

                while ((s = dis.readLine()) != null) {
                    player.dropMessage(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
