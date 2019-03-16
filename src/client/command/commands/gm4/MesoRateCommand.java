package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class MesoRateCommand extends Command {

    {
        setName("mesorate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !mesorate <newrate>");
            return;
        }

        int mesorate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setMesoRate(mesorate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Meso Rate has been changed to " + mesorate + "x."));
    }
}
