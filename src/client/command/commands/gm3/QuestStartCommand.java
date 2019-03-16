package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.quest.MapleQuest;

public class QuestStartCommand extends Command {

    {
        setName("startquest");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !startquest <questid>");
            return;
        }

        int questid = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questid) == 0) {
            MapleQuest quest = MapleQuest.getInstance(questid);

            if (quest != null) {
                int npcid = quest.getNpcRequirement(false);

                quest.forceStart(player, npcid);
                player.dropMessage(5, "QUEST " + questid + " started.");
            } else {
                player.dropMessage(5, "QUESTID " + questid + " is invalid.");
            }
        } else {
            player.dropMessage(5, "QUESTID " + questid + " already started/completed.");
        }
    }
}
