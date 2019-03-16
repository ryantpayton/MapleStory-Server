package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;

public class LeaveEventCommand extends Command {

    {
        setName("leaveevent");
        setDescription("Leave an event.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        int returnMap = player.getSavedLocation("EVENT");

        if (returnMap != -1) {
            if (player.getOla() != null) {
                player.getOla().resetTimes();
                player.setOla(null);
            }

            if (player.getFitness() != null) {
                player.getFitness().resetTimes();
                player.setFitness(null);
            }

            player.saveLocationOnWarp();
            player.changeMap(returnMap);

            if (c.getChannelServer().getEvent() != null) {
                c.getChannelServer().getEvent().addLimit();
            }
        } else {
            player.dropMessage(5, "You are not currently in an event.");
        }
    }
}
