package client.command.commands.gm2;

import client.SkillFactory;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class EmpowerMeCommand extends Command {

    {
        setName("empowerme");

        setDescription(
                "Gives the following buffs:\r\n"
                        + "#L0#Holy Symbol#l\r\n"
                        + "#L1#Bless#l\r\n"
                        + "#L2#Hyper Body#l\r\n"
                        + "#L3#Haste#l\r\n"
                        + "#L4#Magic Guard#l\r\n"
                        + "#L5#Power Guard#l\r\n"
                        + "#L6#Echo of Hero#l\r\n"
                        + "#L7#Invincible#l\r\n"
                        + "#L8#Speed Infusion#l\r\n"
                        + "#L9#Combo#l\r\n"
                        + "#L10#Meso Up#l\r\n"
                        + "#L11#Shadow Partner#l\r\n"
                        + "#L12#Pickpocket#l\r\n"
                        + "#L13#Meso Guard#l\r\n"
                        + "#L14#Maple Warrior#l\r\n"
                        + "#L15#Infinity#l\r\n"
                        + "#L16#Sharp Eyes#l\r\n"
        );
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        final int[] array = {2311003, 2301004, 1301007, 4101004, 2001002, 1101007, 1005, 2301003, 5121009, 1111002, 4111001, 4111002, 4211003, 4211005, 1321000, 2321004, 3121002};

        for (int i : array) {
            SkillFactory.getSkill(i).getEffect(SkillFactory.getSkill(i).getMaxLevel()).applyTo(player);
        }
    }
}
