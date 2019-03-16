package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ServerConstants;

public class LevelCommand extends Command {

    {
        setName("level");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !level <newlevel>");
            return;
        }

        player.loseExp(player.getExp(), false, false);
        player.setLevel(Math.min(Integer.parseInt(params[0]), player.getMaxClassLevel()) - 1);
        player.resetPlayerRates();

        if (ServerConstants.USE_ADD_RATES_BY_LEVEL) {
            player.setPlayerRates();
        }

        player.setWorldRates();
        player.levelUp(false);
    }
}
