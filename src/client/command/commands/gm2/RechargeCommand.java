package client.command.commands.gm2;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import constants.ItemConstants;
import server.MapleItemInformationProvider;

public class RechargeCommand extends Command {

    {
        setName("recharge");
        setDescription("Recharge all use items.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();

        for (Item torecharge : c.getPlayer().getInventory(MapleInventoryType.USE).list()) {
            if (ItemConstants.isThrowingStar(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isArrow(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isBullet(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isConsumable(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            }
        }

        player.dropMessage(5, "USE Recharged.");
    }
}
