package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import tools.MaplePacketCreator;

public class NoticeCommand extends Command {

    {
        setName("notice");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        Server.getInstance().broadcastMessage(c.getWorld(), MaplePacketCreator.serverNotice(6, "[Notice] " + player.getLastCommandMessage()));
    }
}
