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
        short level = 200;
        short hpmp = 30000;

        MapleCharacter player = c.getPlayer();
        player.loseExp(player.getExp(), false, false);
        player.setLevel(level);
        player.resetPlayerRates();

        if (ServerConstants.USE_ADD_RATES_BY_LEVEL) {
            player.setPlayerRates();
        }

        player.setWorldRates();
        player.updateStrDexIntLuk(Short.MAX_VALUE);
        player.setFame(Short.MAX_VALUE);
        player.updateMaxHpMaxMp(hpmp, hpmp);
        player.updateSingleStat(MapleStat.LEVEL, level);
        player.updateSingleStat(MapleStat.FAME, Short.MAX_VALUE);
        player.yellowMessage("Stats maxed out.");
    }
}
