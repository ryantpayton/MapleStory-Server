package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import server.events.gm.MapleEvent;
import server.maps.FieldLimit;

public class JoinEventCommand extends Command {

    {
        setName("joinevent");
        setDescription("Join an event.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (!FieldLimit.CANNOTMIGRATE.check(player.getMap().getFieldLimit())) {
            MapleEvent event = c.getChannelServer().getEvent();

            if (event != null) {
                if (event.getMapId() != player.getMapId()) {
                    if (event.getLimit() > 0) {
                        player.saveLocation("EVENT");

                        if (event.getMapId() == 109080000 || event.getMapId() == 109060001) {
                            player.setTeam(event.getLimit() % 2);
                        }

                        event.minusLimit();

                        player.saveLocationOnWarp();
                        player.changeMap(event.getMapId());
                    } else {
                        player.dropMessage(5, "The limit of players for the event has already been reached.");
                    }
                } else {
                    player.dropMessage(5, "You are already in the event.");
                }
            } else {
                player.dropMessage(5, "There is currently no event in progress.");
            }
        } else {
            player.dropMessage(5, "You are currently in a map where you can't join an event.");
        }
    }
}
