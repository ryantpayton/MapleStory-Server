package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class HealPersonCommand extends Command {

    {
        setName("healperson");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            victim.healHpMp();
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
