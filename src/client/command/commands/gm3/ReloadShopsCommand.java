package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import server.MapleShopFactory;

public class ReloadShopsCommand extends Command {

    {
        setName("reloadshops");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleShopFactory.getInstance().reloadShops();
    }
}
