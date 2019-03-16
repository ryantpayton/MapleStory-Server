package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class TravelRateCommand extends Command {

    {
        setName("travelrate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !travelrate <newrate>");
            return;
        }

        int travelrate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setTravelRate(travelrate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Travel Rate has been changed to " + travelrate + "x."));
    }
}
