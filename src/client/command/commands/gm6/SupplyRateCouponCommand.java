package client.command.commands.gm6;

import client.MapleCharacter;
import client.MapleClient;
import client.command.Command;
import constants.ServerConstants;

public class SupplyRateCouponCommand extends Command {

    {
        setName("supplyratecoupon");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Syntax: !supplyratecoupon <yes|no>");
            return;
        }

        ServerConstants.USE_SUPPLY_RATE_COUPONS = params[0].compareToIgnoreCase("no") != 0;
        player.dropMessage(5, "Rate coupons are now " + (ServerConstants.USE_SUPPLY_RATE_COUPONS ? "enabled" : "disabled") + " for purchase at the Cash Shop.");
    }
}
