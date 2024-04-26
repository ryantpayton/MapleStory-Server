package client.creator.novice;

import client.MapleClient;
import client.MapleJob;
import client.creator.CharacterFactory;
import client.creator.CharacterFactoryRecipe;
import client.inventory.MapleInventoryType;
import constants.ServerConstants;

public class BeginnerCreator extends CharacterFactory {

    private static CharacterFactoryRecipe createRecipe(MapleJob job, int level, int map, int top, int bottom, int shoes, int weapon) {
        CharacterFactoryRecipe recipe = new CharacterFactoryRecipe(job, level, map, top, bottom, shoes, weapon);

        giveItem(recipe, 4161001, 1, MapleInventoryType.ETC);

        return recipe;
    }

    private static void giveItem(CharacterFactoryRecipe recipe, int itemid, int quantity, MapleInventoryType itemType) {
        recipe.addStartingItem(itemid, quantity, itemType);
    }

    public static int createCharacter(MapleClient c, String name, int face, int hair, int skin, int top, int bottom, int shoes, int weapon, int gender) {
        int status;

        if (ServerConstants.SHOW_CUT_SCENES) {
            status = createNewCharacter(c, name, face, hair, skin, gender, createRecipe(MapleJob.BEGINNER, 1, 0, top, bottom, shoes, weapon));
        } else {
            status = createNewCharacter(c, name, face, hair, skin, gender, createRecipe(MapleJob.BEGINNER, 1, 10000, top, bottom, shoes, weapon));
        }

        return status;
    }
}
