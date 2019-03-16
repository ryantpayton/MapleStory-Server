package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import net.server.Server;
import net.server.channel.Channel;

public class OnlineCommand extends Command {

    {
        setName("online");
        setDescription("See a list of all characters currently online.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (Channel ch : Server.getInstance().getChannelsFromWorld(player.getWorld())) {
            player.yellowMessage("Players in Channel " + ch.getId() + ":");

            for (MapleCharacter chr : ch.getPlayerStorage().getAllCharacters()) {
                if (player.isGM()) {
                    player.message(" >> " + MapleCharacter.makeMapleReadable(chr.getName()) + " is at " + chr.getMap().getMapName() + ".");
                } else {
                    if (!chr.isGM()) {
                        player.message(" >> " + MapleCharacter.makeMapleReadable(chr.getName()) + " is at " + chr.getMap().getMapName() + ".");
                    }
                }
            }
        }
    }
}
