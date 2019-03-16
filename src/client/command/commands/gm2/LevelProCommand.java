package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class LevelProCommand extends Command {

    {
        setName("levelpro");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !levelpro <newlevel>");
            return;
        }

        while (player.getLevel() < Math.min(player.getMaxClassLevel(), Integer.parseInt(params[0]))) {
            player.levelUp(false);
        }
    }
}
