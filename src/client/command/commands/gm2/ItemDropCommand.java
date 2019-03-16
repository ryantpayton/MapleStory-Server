package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import constants.ItemConstants;
import constants.ServerConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.javatuples.Quartet;
import server.MapleItemInformationProvider;

public class ItemDropCommand extends Command {

    {
        setName("drop");
        setDescription("Drop an item by id.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("id", true, (List<String>) new ArrayList<String>(), ""),
                        Quartet.with("quantity/#rdays#k", false, (List<String>) new ArrayList<String>(), "#bIf item is a pet then #r[days]#b is required.")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !drop <itemid> <quantity>");
            return;
        }

        int itemId = Integer.parseInt(params[0]);
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();

        if (ii.getName(itemId) == null) {
            player.yellowMessage("Item id '" + params[0] + "' does not exist.");
            return;
        }

        short quantity = 1;

        if (params.length >= 2) {
            quantity = Short.parseShort(params[1]);
        }

        if (ServerConstants.BLOCK_GENERATE_CASH_ITEM && ii.isCash(itemId)) {
            player.yellowMessage("You cannot create a cash item with this command.");
            return;
        }

        if (ItemConstants.isPet(itemId)) {
            // Thanks to istreety & TacoBell
            if (params.length >= 2) {
                quantity = 1;
                long days = Math.max(1, Integer.parseInt(params[1]));
                long expiration = System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000);
                int petid = MaplePet.createPet(itemId);

                Item toDrop = new Item(itemId, (short) 0, quantity, petid);
                toDrop.setExpiration(expiration);

                toDrop.setOwner("");

                if (player.gmLevel() < 3) {
                    byte b = toDrop.getFlag();
                    b |= ItemConstants.ACCOUNT_SHARING;
                    b |= ItemConstants.UNTRADEABLE;
                    b |= ItemConstants.SANDBOX;

                    toDrop.setFlag(b);
                    toDrop.setOwner("TRIAL-MODE");
                }

                c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);

                return;
            } else {
                player.yellowMessage("Pet Syntax: !drop <itemid> <expiration>");
                return;
            }
        }

        Item toDrop;

        if (ItemConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
            toDrop = ii.getEquipById(itemId);
        } else {
            toDrop = new Item(itemId, (short) 0, quantity);
        }

        toDrop.setOwner(player.getName());

        if (player.gmLevel() < 3) {
            byte b = toDrop.getFlag();
            b |= ItemConstants.ACCOUNT_SHARING;
            b |= ItemConstants.UNTRADEABLE;
            b |= ItemConstants.SANDBOX;

            toDrop.setFlag(b);
            toDrop.setOwner("TRIAL-MODE");
        }

        c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);
    }
}
