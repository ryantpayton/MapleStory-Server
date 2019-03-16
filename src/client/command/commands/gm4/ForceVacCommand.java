package client.command.commands.gm4;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.MaplePet;
import client.inventory.manipulator.MapleInventoryManipulator;
import server.maps.MapleMapItem;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import java.util.Arrays;
import java.util.List;

public class ForceVacCommand extends Command {

    {
        setName("forcevac");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        List<MapleMapObject> items = player.getMap().getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.ITEM));

        for (MapleMapObject item : items) {
            MapleMapItem mapItem = (MapleMapItem) item;

            mapItem.lockItem();

            try {
                if (mapItem.isPickedUp()) {
                    continue;
                }

                if (mapItem.getMeso() > 0) {
                    player.gainMeso(mapItem.getMeso(), true);
                } else if (mapItem.getItemId() == 4031865 || mapItem.getItemId() == 4031866) {
                    // Add NX to account, show effect and make item disappear
                    player.getCashShop().gainCash(1, mapItem.getItemId() == 4031865 ? 100 : 250);
                } else if (mapItem.getItem().getItemId() >= 5000000 && mapItem.getItem().getItemId() <= 5000100) {
                    int petId = MaplePet.createPet(mapItem.getItem().getItemId());

                    if (petId == -1) {
                        continue;
                    }

                    MapleInventoryManipulator.addById(c, mapItem.getItem().getItemId(), mapItem.getItem().getQuantity(), null, petId);
                } else {
                    MapleInventoryManipulator.addFromDrop(c, mapItem.getItem(), true);
                }

                player.getMap().pickItemDrop(MaplePacketCreator.removeItemFromMap(mapItem.getObjectId(), 2, player.getId()), mapItem);
            } finally {
                mapItem.unlockItem();
            }
        }
    }
}
