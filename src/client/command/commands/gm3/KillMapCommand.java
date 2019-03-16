package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class KillMapCommand extends Command {

    {
        setName("killmap");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (MapleCharacter mch : player.getMap().getCharacters()) {
            mch.updateHp(0);
        }
    }
}
