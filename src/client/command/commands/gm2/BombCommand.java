package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.server.Server;
import org.javatuples.Quartet;
import server.life.MapleLifeFactory;
import tools.MaplePacketCreator;

public class BombCommand extends Command {

    {
        setName("bomb");
        setDescription("Drops a bomb on someone.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("player", false, (List<String>) new ArrayList<String>(), "#bIf #k[player]#b is left blank the bomb will drop on you.")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length > 0) {
            MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim != null) {
                victim.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(9300166), victim.getPosition());
                Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(5, player.getName() + " used !bomb on " + victim.getName()));
            } else {
                player.message("Player '" + params[0] + "' could not be found on this world.");
            }
        } else {
            player.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(9300166), player.getPosition());
        }
    }
}
