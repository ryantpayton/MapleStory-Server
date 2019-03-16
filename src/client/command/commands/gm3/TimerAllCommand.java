package client.command.commands.gm3;

import client.MapleCharacter;
import client.MapleClient;
import client.command.Command;
import tools.MaplePacketCreator;

public class TimerAllCommand extends Command {

    {
        setName("timerall");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !timerall <seconds>|remove");
            return;
        }

        if (params[0].equalsIgnoreCase("remove")) {
            for (MapleCharacter victim : player.getWorldServer().getPlayerStorage().getAllCharacters()) {
                victim.announce(MaplePacketCreator.removeClock());
            }
        } else {
            try {
                int seconds = Integer.parseInt(params[0]);

                for (MapleCharacter victim : player.getWorldServer().getPlayerStorage().getAllCharacters()) {
                    victim.announce(MaplePacketCreator.getClock(seconds));
                }
            } catch (NumberFormatException e) {
                player.yellowMessage("Syntax: !timerall <seconds>|remove");
            }
        }
    }
}
