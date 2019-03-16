package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.MaplePortal;
import server.maps.MapleMap;

public class JailCommand extends Command {

    {
        setName("jail");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !jail <playername> [<minutes>]");
            return;
        }

        int minutesJailed = 5;

        if (params.length >= 2) {
            minutesJailed = Integer.valueOf(params[1]);

            if (minutesJailed <= 0) {
                player.yellowMessage("Syntax: !jail <playername> [<minutes>]");
                return;
            }
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            victim.addJailExpirationTime(minutesJailed * 60 * 1000);

            int mapid = 300000012;

            // Those gone to jail won't be changing map anyway
            if (victim.getMapId() != mapid) {
                MapleMap target = c.getChannelServer().getMapFactory().getMap(mapid);
                MaplePortal targetPortal = target.getPortal(0);
                victim.saveLocationOnWarp();
                victim.changeMap(target, targetPortal);
                player.message(victim.getName() + " was jailed for " + minutesJailed + " minutes.");
            } else {
                player.message(victim.getName() + "'s time in jail has been extended for " + minutesJailed + " minutes.");
            }
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
