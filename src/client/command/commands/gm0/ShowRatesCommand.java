package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import constants.ServerConstants;

public class ShowRatesCommand extends Command {

    {
        setName("showrates");
        setDescription("See a list of current rates.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        String showMsg = "#eEXP RATE#n" + "\r\n";
        showMsg += "World EXP Rate: #k" + c.getWorldServer().getExpRate() + "x#k" + "\r\n";
        showMsg += "Player EXP Rate: #k" + player.getRawExpRate() + "x#k" + "\r\n";

        if (player.getCouponExpRate() != 1) {
            showMsg += "Coupon EXP Rate: #k" + player.getCouponExpRate() + "x#k" + "\r\n";
        }

        showMsg += "EXP Rate: #e#b" + player.getExpRate() + "x#k#n" + (player.hasNoviceExpRate() ? " - novice rate" : "") + "\r\n";

        showMsg += "\r\n" + "#eMESO RATE#n" + "\r\n";
        showMsg += "World MESO Rate: #k" + c.getWorldServer().getMesoRate() + "x#k" + "\r\n";
        showMsg += "Player MESO Rate: #k" + player.getRawMesoRate() + "x#k" + "\r\n";

        if (player.getCouponMesoRate() != 1) {
            showMsg += "Coupon MESO Rate: #k" + player.getCouponMesoRate() + "x#k" + "\r\n";
        }

        showMsg += "MESO Rate: #e#b" + player.getMesoRate() + "x#k#n" + "\r\n";

        showMsg += "\r\n" + "#eDROP RATE#n" + "\r\n";
        showMsg += "World DROP Rate: #k" + c.getWorldServer().getDropRate() + "x#k" + "\r\n";
        showMsg += "Player DROP Rate: #k" + player.getRawDropRate() + "x#k" + "\r\n";

        if (player.getCouponDropRate() != 1) {
            showMsg += "Coupon DROP Rate: #k" + player.getCouponDropRate() + "x#k" + "\r\n";
        }

        showMsg += "DROP Rate: #e#b" + player.getDropRate() + "x#k#n" + "\r\n";

        showMsg += "\r\n" + "#eBOSS DROP RATE#n" + "\r\n";
        showMsg += "World BOSS DROP Rate: #k" + c.getWorldServer().getBossDropRate() + "x#k" + "\r\n";
        showMsg += "Player DROP Rate: #k" + player.getRawDropRate() + "x#k" + "\r\n";

        if (player.getCouponDropRate() != 1) {
            showMsg += "Coupon DROP Rate: #k" + player.getCouponDropRate() + "x#k" + "\r\n";
        }

        showMsg += "BOSS DROP Rate: #e#b" + player.getBossDropRate() + "x#k#n" + "\r\n";

        if (ServerConstants.USE_QUEST_RATE) {
            showMsg += "\r\n" + "#eQUEST RATE#n" + "\r\n";
            showMsg += "World QUEST Rate: #e#b" + c.getWorldServer().getQuestRate() + "x#k#n" + "\r\n";
        }

        showMsg += "\r\n";
        showMsg += "World TRAVEL Rate: #e#b" + c.getWorldServer().getTravelRate() + "x#k#n" + "\r\n";

        player.showHint(showMsg, 300);
    }
}
