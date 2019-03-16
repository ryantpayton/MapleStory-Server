package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.server.Server;
import org.javatuples.Quartet;
import tools.FilePrinter;
import tools.MaplePacketCreator;

public class ReportBugCommand extends Command {

    {
        setName("reportbug");
        setDescription("Send a bug report.");

        setOtherNames(
                (List<String>) new ArrayList<>(Arrays.asList("bug"))
        );

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("message", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Message too short and not sent. Please do @bug <bug>");
            return;
        }

        String message = player.getLastCommandMessage();
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.sendYellowTip("[BUG]:" + MapleCharacter.makeMapleReadable(player.getName()) + ": " + message));
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(1, message));
        FilePrinter.printError(FilePrinter.COMMAND_BUG, MapleCharacter.makeMapleReadable(player.getName()) + ": " + message);
        player.dropMessage(5, "Your bug '" + message + "' was submitted successfully to our developers. Thank you!");
    }
}
