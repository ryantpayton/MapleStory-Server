package client.inventory;

public enum ItemSlot {
    HAT(100),
    FACE(101),
    EYE(102),
    EARRING(103),
    TOP(104),
    OVERALL(105),
    PANTS(106),
    SHOES(107),
    GLOVE(108),
    SHIELD(109),
    CAPE(110),
    RING(111),
    PENDANT(112),
    BELT(113),
    MEDAL(114),
    SHOULDER(115),
    WEAPON(130); //Note: items that are 130+ are weapons

    public final int value;

    private ItemSlot(int val) {
        this.value = val;
    }
}