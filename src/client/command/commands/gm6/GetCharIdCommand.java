package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class GetCharIdCommand extends Command {

    {
        setName("charid");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.message("Your character id is '" + player.getId() + "'.");
    }
}
