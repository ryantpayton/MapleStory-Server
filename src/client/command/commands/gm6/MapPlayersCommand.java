package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import net.server.world.World;

public class MapPlayersCommand extends Command {

    {
        setName("mapplayers");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        String names = "";
        int map = player.getMapId();

        for (World world : Server.getInstance().getWorlds()) {
            for (MapleCharacter chr : world.getPlayerStorage().getAllCharacters()) {
                int curMap = chr.getMapId();
                String hp = Integer.toString(chr.getHp());
                String maxhp = Integer.toString(chr.getCurrentMaxHp());
                String characterName = chr.getName() + ": " + hp + "/" + maxhp;

                if (map == curMap) {
                    names = names.equals("") ? characterName : (names + ", " + characterName);
                }
            }
        }

        player.message("Players on mapid " + map + ": " + names);
    }
}
