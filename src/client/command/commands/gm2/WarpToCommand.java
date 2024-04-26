package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;
import server.maps.MapleMap;

public class WarpToCommand extends Command {

    {
        setName("warpto");
        setDescription("Move to a player's location.");

        setOtherNames(
                (List<String>) new ArrayList<>(Arrays.asList("reach", "follow"))
        );

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("player", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !reach <playername>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null && victim.isLoggedin()) {
            if (player.getClient().getChannel() != victim.getClient().getChannel()) {
                player.dropMessage(5, "Player '" + victim.getName() + "' is at channel " + victim.getClient().getChannel() + ".");
            } else {
                MapleMap map = victim.getMap();
                player.saveLocationOnWarp();
                player.forceChangeMap(map, map.findClosestPortal(victim.getPosition()));
            }
        } else {
            player.dropMessage(6, "Unknown player.");
        }
    }
}
