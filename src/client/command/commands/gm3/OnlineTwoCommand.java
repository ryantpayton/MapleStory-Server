package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.channel.Channel;

public class OnlineTwoCommand extends Command {

    {
        setName("online2");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        int total = 0;

        for (Channel ch : Server.getInstance().getChannelsFromWorld(player.getWorld())) {
            int size = ch.getPlayerStorage().getAllCharacters().size();
            total += size;
            String s = "(Channel " + ch.getId() + " Online: " + size + ") : ";

            if (ch.getPlayerStorage().getAllCharacters().size() < 50) {
                for (MapleCharacter chr : ch.getPlayerStorage().getAllCharacters()) {
                    s += MapleCharacter.makeMapleReadable(chr.getName()) + ", ";
                }

                player.dropMessage(6, s.substring(0, s.length() - 2));
            }
        }

        //player.dropMessage(6, "There are a total of " + total + " players online.");
        player.showHint("Players online: #e#r" + total + "#k#n.", 300);
    }
}
