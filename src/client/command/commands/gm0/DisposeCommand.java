package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;
import scripting.npc.NPCScriptManager;
import tools.MaplePacketCreator;

public class DisposeCommand extends Command {

    {
        setName("dispose");
        setDescription("Sets your character as disposed so you can talk to NPCs again.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        NPCScriptManager.getInstance().dispose(c);
        c.announce(MaplePacketCreator.enableActions());
        c.removeClickedNPC();
        c.getPlayer().message("You've been disposed.");
    }
}
