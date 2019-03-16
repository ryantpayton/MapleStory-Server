package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.maps.SavedLocationType;

public class ClearSavedLocationsCommand extends Command {

    {
        setName("clearsavelocs");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer(), victim;

        if (params.length > 0) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim == null) {
                player.message("Player '" + params[0] + "' could not be found.");
                return;
            }
        } else {
            victim = c.getPlayer();
        }

        for (SavedLocationType type : SavedLocationType.values()) {
            victim.clearSavedLocation(type);
        }

        player.message("Cleared " + params[0] + "'s saved locations.");
    }
}
