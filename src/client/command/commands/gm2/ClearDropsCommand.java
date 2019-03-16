package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class ClearDropsCommand extends Command {

    {
        setName("cleardrops");
        setDescription("Clears all drops on the map.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.getMap().clearDrops(player);
        player.dropMessage(5, "Cleared dropped items");
    }
}
