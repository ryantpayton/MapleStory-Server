package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;

public class ToggleExpCommand extends Command {

    {
        setName("toggleexp");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        if (c.tryacquireClient()) {
            try {
                c.getPlayer().toggleExpGain();
            } finally {
                c.releaseClient();
            }
        }
    }
}
