package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class DropRateCommand extends Command {

    {
        setName("droprate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !droprate <newrate>");
            return;
        }

        int droprate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setDropRate(droprate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Drop Rate has been changed to " + droprate + "x."));
    }
}
