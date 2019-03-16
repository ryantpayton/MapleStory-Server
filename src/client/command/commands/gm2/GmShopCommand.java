package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import server.MapleShopFactory;

public class GmShopCommand extends Command {

    {
        setName("gmshop");
        setDescription("Opens the GM shop.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleShopFactory.getInstance().getShop(1337).sendShop(c);
    }
}
