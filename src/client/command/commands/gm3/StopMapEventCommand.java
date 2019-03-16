package client.command.commands.gm3;

import client.MapleClient;
import client.command.Command;

public class StopMapEventCommand extends Command {

    {
        setName("stopmapevent");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        c.getPlayer().getMap().setEventStarted(false);
    }
}
