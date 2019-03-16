package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import tools.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UnBanCommand extends Command {

    {
        setName("unban");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !unban <playername>");
            return;
        }

        try {
            Connection con = DatabaseConnection.getConnection();
            int aid = MapleCharacter.getAccountIdByName(params[0]);

            PreparedStatement p = con.prepareStatement("UPDATE accounts SET banned = -1 WHERE id = " + aid);
            p.executeUpdate();

            p = con.prepareStatement("DELETE FROM ipbans WHERE aid = " + aid);
            p.executeUpdate();

            p = con.prepareStatement("DELETE FROM macbans WHERE aid = " + aid);
            p.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            player.message("Failed to unban " + params[0]);
            return;
        }

        player.message("Unbanned " + params[0]);
    }
}
