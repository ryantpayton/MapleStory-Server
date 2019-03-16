package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class NightCommand extends Command {

    {
        setName("night");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.getMap().broadcastNightEffect();
        player.yellowMessage("Done.");
    }
}
