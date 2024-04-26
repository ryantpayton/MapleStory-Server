package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.channel.Channel;
import server.expeditions.MapleExpedition;

import java.util.Map.Entry;

public class ExpedsCommand extends Command {

    {
        setName("expeds");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (Channel ch : Server.getInstance().getChannelsFromWorld(c.getWorld())) {
            if (ch.getExpeditions().isEmpty()) {
                player.yellowMessage("No Expeditions in Channel " + ch.getId());
                continue;
            }

            player.yellowMessage("Expeditions in Channel " + ch.getId());
            int id = 0;

            for (MapleExpedition exped : ch.getExpeditions()) {
                id++;
                player.yellowMessage("> Expedition " + id);
                player.yellowMessage(">> Type: " + exped.getType().toString());
                player.yellowMessage(">> Status: " + (exped.isRegistering() ? "REGISTERING" : "UNDERWAY"));
                player.yellowMessage(">> Size: " + exped.getMembers().size());
                player.yellowMessage(">> Leader: " + exped.getLeader().getName());
                int memId = 2;

                for (Entry<Integer, String> e : exped.getMembers().entrySet()) {
                    if (exped.isLeader(e.getKey())) {
                        continue;
                    }

                    player.yellowMessage(">>> Member " + memId + ": " + e.getValue());
                    memId++;
                }
            }
        }
    }
}
