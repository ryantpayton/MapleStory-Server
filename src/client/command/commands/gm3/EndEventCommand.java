package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class EndEventCommand extends Command {

    {
        setName("endevent");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        c.getChannelServer().setEvent(null);
        player.dropMessage(5, "You have ended the event. No more players may join.");
    }
}
