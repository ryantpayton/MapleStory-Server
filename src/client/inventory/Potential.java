package client.inventory;

import provider.MapleData;
import provider.MapleDataTool;
import server.MapleItemInformationProvider;
import tools.Pair;

import java.util.ArrayList;
import java.util.List;

public class Potential {
    //storage format of potential data:
    /*
    * ItemOption.img
    *  - ID
    *    -type (numeric)
    *    -minLevel (numeric)
    *    -level
    *       -value (numeric)
    *    -slots
    *       -value (bool)
    *    -rarity (numeric)
    * */
    private short id;
    private short type;
    private short[] slots;
    private byte rarity;
    private short reqLevel;
    private PotentialLevelData[] levelData;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short[] getSlots() {
        return slots;
    }

    public void setSlots(short[] slots) {
        this.slots = slots;
    }

    public byte getRarity() {
        return rarity;
    }

    public void setRarity(byte rarity) {
        this.rarity = rarity;
    }

    public short getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(short reqLevel) {
        this.reqLevel = reqLevel;
    }

    private static class PotentialLevelData {
        private short minLevel;
        private short maxLevel;
        private short value;
    }

    public static final int plusSTR = 1, plusDEX = 2, plusINT = 3, plusLUK = 4, plusAS = 5, plusWDEF = 6,
            plusMDEF = 7, plusACC = 8, plusAvoid = 9, plusHP = 10, plusMP = 12;

    public Potential() {
        this.id = -1;
        this.type = -1;
        this.slots = new short[0];
        this.rarity = 0;
        this.reqLevel = 0;
        this.levelData = new PotentialLevelData[0];
    }

    public void load(MapleData data) {
        List<Short> slotData = new ArrayList<>();
        MapleData slots = data.getChildByPath("slots");
        for (MapleData slot : slots.getChildren()) {
            if (MapleDataTool.getInt(slot.getChildByPath("value")) != 0) {
                slotData.add(Short.parseShort(slot.getName()));
            }
        }
        short[] permittedSlots = new short[slotData.size()];
        for (int i = 0; i < slotData.size(); i++) {
            permittedSlots[i] = slotData.get(i);
        }
        List<Pair<Short, Short>> levelData = new ArrayList<>();
        MapleData levels = data.getChildByPath("levels");
        for (MapleData level : levels.getChildren()) {
            levelData.add(new Pair<>(Short.parseShort(level.getName()), (short) MapleDataTool.getInt(level.getChildByPath("value"))));
        }
        PotentialLevelData[] levelDataArray = new PotentialLevelData[levelData.size()];
        for (int i = 0; i < levelData.size()-1; i++) {
            PotentialLevelData potentialLevelData = new PotentialLevelData();
            short minLevel = levelData.get(i).left;
            short value = levelData.get(i).right;
            short maxLevel = (short) (levelData.get(i+1).left-1);
            potentialLevelData.minLevel = minLevel;
            potentialLevelData.maxLevel = maxLevel;
            potentialLevelData.value = value;
            levelDataArray[i] = potentialLevelData;
        }
        PotentialLevelData last = new PotentialLevelData();
        last.minLevel = levelData.getLast().left;
        last.value = levelData.getLast().right;
        last.maxLevel = 999;
        levelDataArray[levelDataArray.length-1] = last;
        short reqLevel = (short) MapleDataTool.getInt(data.getChildByPath("minLevel"));
        short type = (short) MapleDataTool.getInt(data.getChildByPath("type"));
        byte rarity = (byte) MapleDataTool.getInt(data.getChildByPath("rarity"));
        short id = Short.parseShort(data.getName());
        this.id = id;
        this.type = type;
        this.slots = permittedSlots;
        this.rarity = rarity;
        this.reqLevel = reqLevel;
        this.levelData = levelDataArray;
    }

    public static List<Potential> allowed(int itemId, int itemReqLevel, byte rarity) {
        byte bRarity = rarity;
        if (rarity > 8 || rarity < 1 || rarity == 4) {
            return new ArrayList<>();
        }
        if (rarity > 4) {
            bRarity -= 4;
        }
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        List<Potential> allowed = new ArrayList<>();
        for (Potential potential: ii.potentials.values()) {
            if (potential.reqLevel <= itemReqLevel && potential.rarity == bRarity) {
                short slotId = (short) (itemId / 10000);
                for (short allowedSlot : potential.slots) {
                    if (allowedSlot == slotId) {
                        allowed.add(potential);
                        break;
                    }
                }
            }
        }
        return allowed;
    }
}
