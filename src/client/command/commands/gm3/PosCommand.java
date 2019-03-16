package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class PosCommand extends Command {

    {
        setName("pos");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        float xpos = player.getPosition().x;
        float ypos = player.getPosition().y;
        float fh = player.getMap().getFootholds().findBelow(player.getPosition()).getId();

        player.dropMessage(6, "Position: (" + xpos + ", " + ypos + ")");
        player.dropMessage(6, "Foothold ID: " + fh);
    }
}
