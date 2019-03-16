package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MaplePlayerNPC;

public class PlayerNpcRemoveCommand extends Command {

    {
        setName("playernpcremove");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !playernpcremove <playername>");
            return;
        }

        MaplePlayerNPC.removePlayerNPC(c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]));
    }
}
