package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.inventory.manipulator.MapleInventoryManipulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class ClearSlotCommand extends Command {

    {
        setName("clearslot");
        setDescription("Clears all items from desired item tab.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("type", true, (List<String>) new ArrayList<>(Arrays.asList("all", "equip", "use", "setup", "etc", "cash")), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !clearslot <all, equip, use, setup, etc or cash.>");
            return;
        }

        String type = params[0];

        switch (type) {
            case "all":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.EQUIP, (byte) i, tempItem.getQuantity(), false, false);
                }

                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.USE).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (byte) i, tempItem.getQuantity(), false, false);
                }

                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.ETC).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, (byte) i, tempItem.getQuantity(), false, false);
                }

                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.SETUP).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.SETUP, (byte) i, tempItem.getQuantity(), false, false);
                }

                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.CASH, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("All Slots Cleared.");
                break;
            case "equip":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.EQUIP, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("Equipment Slot Cleared.");
                break;
            case "use":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.USE).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("Use Slot Cleared.");
                break;
            case "setup":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.SETUP).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.SETUP, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("Set-Up Slot Cleared.");
                break;
            case "etc":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.ETC).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("ETC Slot Cleared.");
                break;
            case "cash":
                for (int i = 0; i < 101; i++) {
                    Item tempItem = c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((byte) i);

                    if (tempItem == null) {
                        continue;
                    }

                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.CASH, (byte) i, tempItem.getQuantity(), false, false);
                }

                player.yellowMessage("Cash Slot Cleared.");
                break;
            default:
                player.yellowMessage("Slot" + type + " does not exist!");
                break;
        }
    }
}
