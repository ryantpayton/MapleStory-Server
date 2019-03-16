package client.command.commands.gm3;

import client.MapleClient;
import client.command.Command;

public class StartMapEventCommand extends Command {

    {
        setName("startmapevent");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        c.getPlayer().getMap().startEvent(c.getPlayer());
    }
}
