package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.world.World;

public class DCAllCommand extends Command {

    {
        setName("dcall");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (World world : Server.getInstance().getWorlds()) {
            for (MapleCharacter chr : world.getPlayerStorage().getAllCharacters()) {
                if (!chr.isGM()) {
                    chr.getClient().disconnect(false, false);
                }
            }
        }

        player.message("All players successfully disconnected.");
    }
}
