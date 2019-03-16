package client.command.commands.gm2;

import client.MapleStat;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ServerConstants;

public class MaxStatCommand extends Command {

    {
        setName("maxstat");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.loseExp(player.getExp(), false, false);
        player.setLevel(255);
        player.resetPlayerRates();

        if (ServerConstants.USE_ADD_RATES_BY_LEVEL) {
            player.setPlayerRates();
        }

        player.setWorldRates();
        player.updateStrDexIntLuk(Short.MAX_VALUE);
        player.setFame(13337);
        player.updateMaxHpMaxMp(30000, 30000);
        player.updateSingleStat(MapleStat.LEVEL, 255);
        player.updateSingleStat(MapleStat.FAME, 13337);
        player.yellowMessage("Stats maxed out.");
    }
}
