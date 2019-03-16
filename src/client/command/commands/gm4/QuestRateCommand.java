package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.MaplePacketCreator;

public class QuestRateCommand extends Command {

    {
        setName("questrate");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !questrate <newrate>");
            return;
        }

        int questrate = Math.max(Integer.parseInt(params[0]), 1);

        c.getWorldServer().setQuestRate(questrate);
        c.getWorldServer().broadcastPacket(MaplePacketCreator.serverNotice(6, "[Rate] Quest Rate has been changed to " + questrate + "x."));
    }
}
