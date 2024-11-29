package com.kasouza.lilo.shared.infra.cli;

import java.util.Map;

public class Arguments {
    private String commandName;
    private String commandArgument;
    private Map<Option, String> options;

    public Arguments(String commandName, String commandArgument, Map<Option, String> optionStrings) {
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.options = optionStrings;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public Map<Option, String> getOptions() {
        return options;
    }
}
