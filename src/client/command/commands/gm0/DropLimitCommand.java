package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;
import constants.ServerConstants;

public class DropLimitCommand extends Command {

    {
        setName("droplimit");
        setDescription("See how many drops are present on the map, compared to the max allowed before they start disappearing.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        int dropCount = c.getPlayer().getMap().getDroppedItemCount();

        if (((float) dropCount) / ServerConstants.ITEM_LIMIT_ON_MAP < 0.75f) {
            c.getPlayer().showHint("Current drop count: #b" + dropCount + "#k / #e" + ServerConstants.ITEM_LIMIT_ON_MAP + "#n", 300);
        } else {
            c.getPlayer().showHint("Current drop count: #r" + dropCount + "#k / #e" + ServerConstants.ITEM_LIMIT_ON_MAP + "#n", 300);
        }
    }
}
