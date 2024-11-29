package com.kasouza.lilo.shared.infra.cli;

import java.util.HashMap;
import java.util.List;

public class ArgParser {
    public Arguments parse(String[] args, List<OptDef> optDefs) {
        String command = null;
        String commandArgument = null;
        var options = new HashMap<Option, String>();

        for (int i = 0; i < args.length; i++) {
            var arg = args[i].trim();
            if (arg.startsWith("-")) {
                String[] parts = arg.split("=");

                String optionKey = parts[0];
                String optionValue = null;

                if (parts.length > 1) {
                    if (parts.length != 2) {
                        System.err.println("Invalid argument " + arg);
                        return null;
                    }

                    optionValue = parts[1];
                }

                var foundOptDefOptional = optDefs.stream()
                        .filter(optDef -> {
                            var shortName = optDef.getShortName();
                            return (shortName != null && shortName.equals(optionKey))
                                    || optDef.getLongName().equals(optionKey);
                        })
                        .findFirst();

                if (foundOptDefOptional.isEmpty()) {
                    System.err.println("Invalid argument " + optionKey);
                    return null;
                }

                var foundOptDef = foundOptDefOptional.get();

                if (foundOptDef.hasArgs()) {
                    if (optionValue == null) {
                        optionValue = args[i + 1];
                        i++;

                        if (optionValue.startsWith("-")) {
                            System.err.println("Invalid option value " + optionValue);
                            return null;
                        }
                    }

                } else if (optionValue != null) {
                    System.err.println("Giving value to option that does not accept a value " + optionKey);
                    return null;
                }

                options.put(foundOptDef.getOption(), optionValue);
            } else if (command == null) {
                command = arg;
            } else if (commandArgument == null) {
                commandArgument = arg;
            } else {
                System.err.println("Invalid arguments near " + arg);
                return null;
            }
        }

        return new Arguments(command, commandArgument, options);
    }
}
