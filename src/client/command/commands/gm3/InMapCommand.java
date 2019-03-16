package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class InMapCommand extends Command {

    {
        setName("inmap");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        String st = "";

        for (MapleCharacter chr : player.getMap().getCharacters()) {
            st += chr.getName() + " ";
        }

        player.message(st);
    }
}
