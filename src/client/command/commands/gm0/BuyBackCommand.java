package client.command.commands.gm0;

import client.MapleClient;
import client.command.Command;
import client.processor.BuybackProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class BuyBackCommand extends Command {

    {
        setName("buyback");
        setDescription("Shows current buyback status and option to use buyback.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("info/now", false, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        if (params.length < 1) {
            c.getPlayer().yellowMessage("Syntax: @buyback <info|now>");
            return;
        }

        if (params[0].contentEquals("now")) {
            BuybackProcessor.processBuyback(c);
        } else {
            c.getPlayer().showBuybackInfo();
        }
    }
}
