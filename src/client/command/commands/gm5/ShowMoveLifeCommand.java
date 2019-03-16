package client.command.commands.gm5;

import client.command.Command;
import client.MapleClient;
import constants.ServerConstants;

public class ShowMoveLifeCommand extends Command {

    {
        setName("showmovelife");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        ServerConstants.USE_DEBUG_SHOW_RCVD_MVLIFE = !ServerConstants.USE_DEBUG_SHOW_RCVD_MVLIFE;
    }
}
