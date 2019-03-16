package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import tools.MaplePacketCreator;

public class MaxEnergyCommand extends Command {

    {
        setName("maxenergy");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        //MapleCharacter player = c.getPlayer();
        c.getPlayer().setDojoEnergy(10000);
        c.announce(MaplePacketCreator.getEnergy("energy", 10000));
    }
}
