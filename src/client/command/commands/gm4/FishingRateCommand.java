package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class FishingRateCommand extends Command {

    {
        setName("fishrate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !fishrate <newrate>");
            return;
        }

        int fishrate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setFishingRate(fishrate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Fishing Rate has been changed to " + fishrate + "x."));
    }
}
