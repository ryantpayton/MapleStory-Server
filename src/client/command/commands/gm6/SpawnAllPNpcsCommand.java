package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MaplePlayerNPC;

public class SpawnAllPNpcsCommand extends Command {

    {
        setName("spawnallpnpcs");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MaplePlayerNPC.multicastSpawnPlayerNPC(player.getMapId(), player.getWorld());
    }
}
