package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import constants.ServerConstants;

public class RatesCommand extends Command {

    {
        setName("rates");
        setDescription("See a list of player current rates.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        // Travel rates not applicable since it's intrinsically a server/environment rate rather than a character rate
        String showMsg_ = "#eCHARACTER RATES#n" + "\r\n\r\n";
        showMsg_ += "EXP Rate: #e#b" + player.getExpRate() + "x#k#n" + (player.hasNoviceExpRate() ? " - novice rate" : "") + "\r\n";
        showMsg_ += "MESO Rate: #e#b" + player.getMesoRate() + "x#k#n" + "\r\n";
        showMsg_ += "DROP Rate: #e#b" + player.getDropRate() + "x#k#n" + "\r\n";
        showMsg_ += "BOSS DROP Rate: #e#b" + player.getBossDropRate() + "x#k#n" + "\r\n";

        if (ServerConstants.USE_QUEST_RATE) {
            showMsg_ += "QUEST Rate: #e#b" + c.getWorldServer().getQuestRate() + "x#k#n" + "\r\n";
        }

        player.showHint(showMsg_, 300);
    }
}
