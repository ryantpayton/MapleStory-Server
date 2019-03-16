package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;

public class ToggleCouponCommand extends Command {

    {
        setName("togglecoupon");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !togglecoupon <itemid>");
            return;
        }

        Server.getInstance().toggleCoupon(Integer.parseInt(params[0]));
    }
}
