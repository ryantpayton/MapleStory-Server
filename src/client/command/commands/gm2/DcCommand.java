package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class DcCommand extends Command {

    {
        setName("dc");
        setDescription("Disconnects a desired player.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("player", true, (List<String>) new ArrayList<String>(), "#bIf #k[player]#b is a higher GM level than you then you will be disconnected.")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !dc <playername>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim == null) {
            victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim == null) {
                victim = player.getMap().getCharacterByName(params[0]);

                if (victim != null) {
                    // Sometimes bugged because the map = null
                    try {
                        victim.getClient().disconnect(true, false);
                        player.getMap().removePlayer(victim);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        }

        if (player.gmLevel() < victim.gmLevel()) {
            victim = player;
        }

        victim.getClient().disconnect(false, false);
    }
}
