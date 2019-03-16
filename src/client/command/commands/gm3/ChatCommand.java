package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class ChatCommand extends Command {

    {
        setName("togglewhitechat");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.toggleWhiteChat();
        player.message("Your chat is now " + (player.getWhiteChat() ? " white" : "normal") + ".");
    }
}
