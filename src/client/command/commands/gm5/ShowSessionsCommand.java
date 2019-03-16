package client.command.commands.gm5;

import client.MapleClient;
import client.command.Command;
import net.server.coordinator.MapleSessionCoordinator;

public class ShowSessionsCommand extends Command {

    {
        setName("showsessions");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleSessionCoordinator.getInstance().printSessionTrace(c);
    }
}
