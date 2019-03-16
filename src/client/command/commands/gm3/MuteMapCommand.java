package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class MuteMapCommand extends Command {

    {
        setName("mutemap");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (player.getMap().isMuted()) {
            player.getMap().setMuted(false);
            player.dropMessage(5, "The map you are in has been un-muted.");
        } else {
            player.getMap().setMuted(true);
            player.dropMessage(5, "The map you are in has been muted.");
        }
    }
}
