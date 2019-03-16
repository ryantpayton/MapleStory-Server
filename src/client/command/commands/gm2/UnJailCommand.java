package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class UnJailCommand extends Command {

    {
        setName("unjail");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !unjail <playername>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            if (victim.getJailExpirationTimeLeft() <= 0) {
                player.message("This player is already free.");
                return;
            }

            victim.removeJailExpirationTime();
            victim.message("By lack of concrete proof you are now unjailed. Enjoy freedom!");
            player.message(victim.getName() + " was unjailed.");
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
