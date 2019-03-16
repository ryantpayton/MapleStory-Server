package client.command.commands.gm5;

import java.util.Collection;
import client.MapleClient;
import client.MapleCharacter;
import client.command.Command;
import constants.GameConstants;
import net.server.Server;
import net.server.world.World;

public class IpListCommand extends Command {

    {
        setName("iplist");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        String str = "Player-IP relation:";

        for (World w : Server.getInstance().getWorlds()) {
            Collection<MapleCharacter> chars = w.getPlayerStorage().getAllCharacters();

            if (!chars.isEmpty()) {
                str += "\r\n" + GameConstants.WORLD_NAMES[w.getId()] + "\r\n";

                for (MapleCharacter chr : chars) {
                    str += "  " + chr.getName() + " - " + chr.getClient().getSession().getRemoteAddress() + "\r\n";
                }
            }
        }

        c.getAbstractPlayerInteraction().npcTalk(22000, str);
    }
}
