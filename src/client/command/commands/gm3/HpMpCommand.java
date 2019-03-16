package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class HpMpCommand extends Command {

    {
        setName("hpmp");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleCharacter victim = player;
        int statUpdate = 1;

        if (params.length == 2) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            statUpdate = Integer.valueOf(params[1]);
        } else if (params.length == 1) {
            statUpdate = Integer.valueOf(params[0]);
        } else {
            player.yellowMessage("Syntax: !hpmp [<playername>] <value>");
        }

        if (victim != null) {
            victim.updateHpMp(statUpdate);
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
