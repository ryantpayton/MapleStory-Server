package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import tools.MaplePacketCreator;

public class UnBugCommand extends Command {

    {
        setName("unbug");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.enableActions());
    }
}
