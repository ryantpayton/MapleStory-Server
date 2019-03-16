package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class ServerMessageCommand extends Command {

    {
        setName("servermessage");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        c.getWorldServer().setServerMessage(player.getLastCommandMessage());
    }
}
