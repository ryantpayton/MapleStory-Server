package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;
import net.server.coordinator.MapleLoginBypassCoordinator;

public class EnableAuthCommand extends Command {

    {
        setName("enableauth");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        if (c.tryacquireClient()) {
            try {
                MapleLoginBypassCoordinator.getInstance().unregisterLoginBypassEntry(c.getNibbleHWID(), c.getAccID());
            } finally {
                c.releaseClient();
            }
        }
    }
}
