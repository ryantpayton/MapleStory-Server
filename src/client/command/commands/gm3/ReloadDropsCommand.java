package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleMonsterInformationProvider;

public class ReloadDropsCommand extends Command {

    {
        setName("reloaddrops");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleMonsterInformationProvider.getInstance().clearDrops();
        player.dropMessage(5, "Reloaded Drops");
    }
}
