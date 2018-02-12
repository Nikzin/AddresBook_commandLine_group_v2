package com.loca.addressbook.userinterface;

import java.util.ArrayList;
import java.util.List;

public class CommandLine {
    private String command;
    private List<String> parameters = new ArrayList<>();

    private CommandLine(String command, List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    static CommandLine parse(String commandLine) {
        List<String> parameters = new ArrayList<>();
        String[] commandLineParts = commandLine.split(" ");
        String command = commandLineParts[0];

        int i = 1;
        while (i < commandLineParts.length) {
            parameters.add(commandLineParts[i]);
            i++;
        }

        return new CommandLine(command, parameters);
    }

    public String getCommand() {
        return command;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
