package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.quest.MapleQuest;

public class ClearQuestCommand extends Command {

    {
        setName("clearquest");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Please include a quest ID.");
            return;
        }

        MapleQuest.clearCache(Integer.parseInt(params[0]));
        player.dropMessage(5, "Quest Cache for quest " + params[0] + " cleared.");
    }
}
