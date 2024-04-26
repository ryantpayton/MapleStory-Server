package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpCommand extends Command {

    {
        setName("commands");
        setDescription("See a list of available commands.");

        setOtherNames(
                (List<String>) new ArrayList<>(Arrays.asList("help", "playercommands"))
        );
    }

    @Override
    public void execute(MapleClient client, String[] params) {
        client.getAbstractPlayerInteraction().openNpc(9201143, "commands");
    }
}
