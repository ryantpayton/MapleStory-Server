package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.world.World;
import tools.MaplePacketCreator;

public class SaveAllCommand extends Command {

    {
        setName("saveall");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (World world : Server.getInstance().getWorlds()) {
            for (MapleCharacter chr : world.getPlayerStorage().getAllCharacters()) {
                chr.saveCharToDB();
            }
        }

        String message = player.getName() + " used !saveall.";
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(5, message));
        player.message("All players saved successfully.");
    }
}
