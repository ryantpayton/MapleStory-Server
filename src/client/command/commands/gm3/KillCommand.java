package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import tools.MaplePacketCreator;

public class KillCommand extends Command {

    {
        setName("kill");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !kill <playername>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            victim.updateHpMp(0);
            Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(5, player.getName() + " used !kill on " + victim.getName()));
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
