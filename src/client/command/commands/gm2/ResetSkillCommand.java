package client.command.commands.gm2;

import client.*;
import client.command.Command;
import provider.MapleData;
import provider.MapleDataProviderFactory;

import java.io.File;

public class ResetSkillCommand extends Command {

    {
        setName("resetskill");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (MapleData skill_ : MapleDataProviderFactory.getDataProvider(new File(System.getProperty("wzpath") + "/" + "String.wz")).getData("Skill.img").getChildren()) {
            try {
                Skill skill = SkillFactory.getSkill(Integer.parseInt(skill_.getName()));
                player.changeSkillLevel(skill, (byte) 0, skill.getMaxLevel(), -1);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                break;
            } catch (NullPointerException npe) {
                // TODO: Blank?
            }
        }

        if (player.getJob().isA(MapleJob.ARAN1) || player.getJob().isA(MapleJob.LEGEND)) {
            Skill skill = SkillFactory.getSkill(5001005);
            player.changeSkillLevel(skill, (byte) -1, -1, -1);
        } else {
            Skill skill = SkillFactory.getSkill(21001001);
            player.changeSkillLevel(skill, (byte) -1, -1, -1);
        }

        player.yellowMessage("Skills reseted.");
    }
}
