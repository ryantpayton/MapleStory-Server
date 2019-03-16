package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.quest.MapleQuest;

public class QuestResetCommand extends Command {

    {
        setName("resetquest");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !resetquest <questid>");
            return;
        }

        int questid_ = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questid_) != 0) {
            MapleQuest quest = MapleQuest.getInstance(questid_);

            if (quest != null) {
                quest.reset(player);
                player.dropMessage(5, "QUEST " + questid_ + " reseted.");
            } else {
                // Should not occur
                player.dropMessage(5, "QUESTID " + questid_ + " is invalid.");
            }
        }
    }
}
