package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import server.life.MaplePlayerNPC;

public class EraseAllPNpcsCommand extends Command {

    {
        setName("eraseallpnpcs");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MaplePlayerNPC.removeAllPlayerNPC();
    }
}
