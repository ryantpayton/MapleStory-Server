package client.command.commands.gm3;

import client.MapleCharacter;
import client.MapleClient;
import client.command.Command;
import tools.MaplePacketCreator;

public class TimerCommand extends Command {

    {
        setName("timer");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 2) {
            player.yellowMessage("Syntax: !timer <playername> <seconds>|remove");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            if (params[1].equalsIgnoreCase("remove")) {
                victim.announce(MaplePacketCreator.removeClock());
            } else {
                try {
                    victim.announce(MaplePacketCreator.getClock(Integer.parseInt(params[1])));
                } catch (NumberFormatException e) {
                    player.yellowMessage("Syntax: !timer <playername> <seconds>|remove");
                }
            }
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
