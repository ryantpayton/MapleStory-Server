package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;
import constants.ServerConstants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeCommand extends Command {

    {
        setDescription("");
    }

    @Override
    public void execute(MapleClient client, String[] params) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getDefault());

        client.getPlayer().yellowMessage(ServerConstants.NAME + " Server Time: " + dateFormat.format(new Date()));
    }
}
