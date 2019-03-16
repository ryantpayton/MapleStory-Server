package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.quest.MapleQuest;

public class QuestCompleteCommand extends Command {

    {
        setName("completequest");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !completequest <questid>");
            return;
        }

        int questId = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questId) == 1) {
            MapleQuest quest = MapleQuest.getInstance(questId);

            if (quest != null) {
                int npcid = quest.getNpcRequirement(true);

                quest.forceComplete(player, npcid);
                player.dropMessage(5, "QUEST " + questId + " completed.");
            } else {
                // Should not occur
                player.dropMessage(5, "QUESTID " + questId + " is invalid.");
            }
        } else {
            player.dropMessage(5, "QUESTID " + questId + " not started or already completed.");
        }
    }
}
