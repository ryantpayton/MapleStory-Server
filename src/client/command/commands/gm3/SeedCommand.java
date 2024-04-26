package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.Item;

import java.awt.*;

public class SeedCommand extends Command {

    {
        setName("seed");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (player.getMapId() != 910010000) {
            player.yellowMessage("This command can only be used in HPQ.");
            return;
        }

        Point pos[] = {new Point(7, -207), new Point(179, -447), new Point(-3, -687), new Point(-357, -687), new Point(-538, -447), new Point(-359, -207)};
        int seed[] = {4001097, 4001096, 4001095, 4001100, 4001099, 4001098};

        for (int i = 0; i < pos.length; i++) {
            Item item = new Item(seed[i], (byte) 0, (short) 1);
            player.getMap().spawnItemDrop(player, player, item, pos[i], false, true);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
