package client.command.commands.gm5;

import client.command.Command;
import client.MapleClient;
import constants.ServerConstants;

public class ShowPacketsCommand extends Command {

    {
        setName("showpackets");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        ServerConstants.USE_DEBUG_SHOW_RCVD_PACKET = !ServerConstants.USE_DEBUG_SHOW_RCVD_PACKET;
    }
}
