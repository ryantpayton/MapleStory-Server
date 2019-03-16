package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import server.MapleItemInformationProvider;
import server.life.MapleMonsterInformationProvider;
import tools.DatabaseConnection;
import tools.Pair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.javatuples.Quartet;

public class WhoDropsCommand extends Command {

    {
        setName("whodrops");
        setDescription("See what monsters drop a certain item.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("item", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Please do @whodrops <item name>");
            return;
        }

        if (c.tryacquireClient()) {
            try {
                String searchString = player.getLastCommandMessage();
                String output = "";
                Iterator<Pair<Integer, String>> listIterator = MapleItemInformationProvider.getInstance().getItemDataByName(searchString).iterator();

                if (listIterator.hasNext()) {
                    int count = 1;

                    while (listIterator.hasNext() && count <= 3) {
                        Pair<Integer, String> data = listIterator.next();
                        output += "#b" + data.getRight() + "#k is dropped by:\r\n";

                        try {
                            Connection con = DatabaseConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement("SELECT dropperid FROM drop_data WHERE itemid = ? LIMIT 50");
                            ps.setInt(1, data.getLeft());
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                String resultName = MapleMonsterInformationProvider.getInstance().getMobNameFromId(rs.getInt("dropperid"));

                                if (resultName != null) {
                                    output += resultName + ", ";
                                }
                            }

                            rs.close();
                            ps.close();
                            con.close();
                        } catch (Exception e) {
                            player.dropMessage(6, "There was a problem retrieving the required data. Please try again.");
                            e.printStackTrace();
                            return;
                        }

                        output += "\r\n\r\n";
                        count++;
                    }
                } else {
                    player.dropMessage(5, "The item you searched for doesn't exist.");
                    return;
                }

                c.getAbstractPlayerInteraction().npcTalk(9010000, output);
            } finally {
                c.releaseClient();
            }
        } else {
            player.dropMessage(5, "Please wait a while for your request to be processed.");
        }
    }
}
