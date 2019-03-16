package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MaplePlayerNPC;

public class PlayerNpcCommand extends Command {

    {
        setName("playernpc");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !playernpc <playername>");
            return;
        }

        if (!MaplePlayerNPC.spawnPlayerNPC(player.getMapId(), player.getPosition(), c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]))) {
            player.dropMessage(5, "Could not deploy PlayerNPC. Either there's no room available here or depleted out scriptids to use.");
        }
    }
}
