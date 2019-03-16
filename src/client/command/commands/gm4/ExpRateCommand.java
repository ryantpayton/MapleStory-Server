package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class ExpRateCommand extends Command {

    {
        setName("exprate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !exprate <newrate>");
            return;
        }

        int exprate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setExpRate(exprate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Exp Rate has been changed to " + exprate + "x."));
    }
}
