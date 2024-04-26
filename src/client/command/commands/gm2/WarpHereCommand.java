package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.maps.MapleMap;
import net.server.Server;
import net.server.channel.Channel;
import org.javatuples.Quartet;

public class WarpHereCommand extends Command {

    {
        setName("warphere");
        setDescription("Move a player to your location.");

        setOtherNames(
                (List<String>) new ArrayList<>(Arrays.asList("summon"))
        );

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("player", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !warphere <playername>");
            return;
        }

        MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim == null) {
            // If victim isn't on current channel, loop all channels on current world.

            for (Channel ch : Server.getInstance().getChannelsFromWorld(c.getWorld())) {
                victim = ch.getPlayerStorage().getCharacterByName(params[0]);

                if (victim != null) {
                    // We found the person, no need to continue the loop.
                    break;
                }
            }
        }

        if (victim != null) {
            if (!victim.isLoggedinWorld()) {
                player.dropMessage(6, "Player currently not logged in or unreachable.");
                return;
            }

            // And then change channel if needed.
            if (player.getClient().getChannel() != victim.getClient().getChannel()) {
                victim.dropMessage("Changing channel, please wait a moment.");
                victim.getClient().changeChannel(player.getClient().getChannel());
            }

            try {
                // Poll for a while until the player reconnects
                for (int i = 0; i < 7; i++) {
                    if (victim.isLoggedinWorld()) {
                        break;
                    }

                    Thread.sleep(1777);
                }
            } catch (InterruptedException e) {
                // TODO: Blank?
            }

            MapleMap map = player.getMap();
            victim.saveLocationOnWarp();
            victim.forceChangeMap(map, map.findClosestPortal(player.getPosition()));
        } else {
            player.dropMessage(6, "Unknown player.");
        }
    }
}
