package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.quest.MapleQuest;

public class ClearQuestCacheCommand extends Command {

    {
        setName("clearquestcache");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleQuest.clearCache();
        player.dropMessage(5, "Quest Cache Cleared.");
    }
}
