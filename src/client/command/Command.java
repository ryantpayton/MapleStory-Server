package client.command;

import client.MapleClient;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Quartet;

public abstract class Command {

    protected String name = "";
    protected String description = "";
    protected List<String> otherNames = new ArrayList<>();
    protected List<Quartet<String, Boolean, List<String>, String>> parameters = new ArrayList<>();

    public abstract void execute(MapleClient client, String[] params);

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    protected void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }

    public List<Quartet<String, Boolean, List<String>, String>> getParameters() {
        return parameters;
    }

    protected void setParameters(List<Quartet<String, Boolean, List<String>, String>> parameters) {
        this.parameters = parameters;
    }

    protected String joinStringFrom(String arr[], int start) {
        StringBuilder builder = new StringBuilder();

        for (int i = start; i < arr.length; i++) {
            builder.append(arr[i]);

            if (i != arr.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}
