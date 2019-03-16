package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;

public class EquipLvCommand extends Command {

    {
        setName("equiplv");
        setDescription("See a list of all equipped items that can level up and their current progress.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        c.getPlayer().showAllEquipFeatures();
    }
}
