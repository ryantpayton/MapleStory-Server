package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class GetAccCommand extends Command {

    {
        setName("getacc");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !getacc <playername>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            player.message(victim.getName() + "'s account name is " + victim.getClient().getAccountName() + ".");
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
