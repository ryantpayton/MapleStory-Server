package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;
import server.MapleItemInformationProvider;
import server.gachapon.MapleGachapon;

public class GachaCommand extends Command {

    private final String[] gatchaNames = {"Henesys", "Ellinia", "Perion", "Kerning City", "Sleepywood", "Mushroom Shrine", "Showa Spa Male", "Showa Spa Female", "New Leaf City", "Nautilus Harbor"};

    {
        setName("gacha");
        setDescription("See a list of unique items obtained from a selected gachapon.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with(
                                "name",
                                true,
                                (List<String>) new ArrayList<>(
                                        Arrays.asList(
                                                gatchaNames
                                        )
                                ),
                                ""
                        )
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        int[] ids = {9100100, 9100101, 9100102, 9100103, 9100104, 9100105, 9100106, 9100107, 9100109, 9100117};
        String search = c.getPlayer().getLastCommandMessage();

        String gachaName = "";
        MapleGachapon.Gachapon gacha = null;

        for (int i = 0; i < gatchaNames.length; i++) {
            if (search.equalsIgnoreCase(gatchaNames[i])) {
                gachaName = gatchaNames[i];
                gacha = MapleGachapon.Gachapon.getByNpcId(ids[i]);
                break;
            }
        }

        String talkStr;

        if (gacha == null) {
            talkStr = "#bSyntax:\r\n#k";
            talkStr += "!gacha #r[name]\r\n\r\n#k";
            talkStr += "#r[name]#k:\r\n";

            for (int i = 0; i < gatchaNames.length; i++) {
                talkStr += "#L" + i + "#" + gatchaNames[i] + "#l\r\n";
            }

            talkStr += "\r\n\r\n#rRed parameters are required!";
        } else {
            talkStr = "The #b" + gachaName + " Gachapon#k contains the following items:\r\n";

            for (int i = 0; i < 2; i++) {
                for (int id : gacha.getItems(i)) {
                    talkStr += "#L" + id + "#" + MapleItemInformationProvider.getInstance().getName(id) + "#l\r\n";
                }
            }

            talkStr += "\r\n\r\nPlease keep in mind that there are items that are in all gachapons and are not listed here.";
        }

        c.getAbstractPlayerInteraction().npcTalk(9010000, talkStr);
    }
}
