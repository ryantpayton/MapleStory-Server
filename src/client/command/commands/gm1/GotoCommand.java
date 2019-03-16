package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import constants.GameConstants;
import java.util.ArrayList;
import java.util.Arrays;
import server.MaplePortal;
import server.maps.FieldLimit;
import server.maps.MapleMap;
import server.maps.MapleMiniDungeonInfo;
import java.util.HashMap;
import java.util.List;
import org.javatuples.Quartet;

public class GotoCommand extends Command {

    {
        setName("goto");
        setDescription("Go to a map based on map name.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("map", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: @goto <map name>");
            return;
        }

        if (!player.isAlive()) {
            player.dropMessage(1, "This command cannot be used when you're dead.");
            return;
        }

        if (!player.isGM()) {
            if (player.getEventInstance() != null || MapleMiniDungeonInfo.isDungeonMap(player.getMapId()) || FieldLimit.CANNOTMIGRATE.check(player.getMap().getFieldLimit())) {
                player.dropMessage(1, "This command can not be used in this map.");
                return;
            }
        }

        HashMap<String, Integer> gotomaps;

        if (player.isGM()) {
            gotomaps = new HashMap<>(GameConstants.GOTO_AREAS); // Distinct map registry for GM/users suggested thanks to Vcoc
        } else {
            gotomaps = new HashMap<>(GameConstants.GOTO_TOWNS);
        }

        if (gotomaps.containsKey(params[0])) {
            MapleMap target = c.getChannelServer().getMapFactory().getMap(gotomaps.get(params[0]));

            // Expedition issue with this command detected thanks to Masterrulax
            MaplePortal targetPortal = target.getRandomPlayerSpawnpoint();
            player.saveLocationOnWarp();
            player.changeMap(target, targetPortal);
        } else {
            player.dropMessage(5, "Area '" + params[0] + "' is not registered.");
        }
    }
}
