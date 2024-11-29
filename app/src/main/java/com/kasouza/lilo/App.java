package com.kasouza.lilo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kasouza.lilo.shared.infra.cli.ArgParser;
import com.kasouza.lilo.shared.infra.cli.Arguments;
import com.kasouza.lilo.shared.infra.cli.OptDef;
import com.kasouza.lilo.shared.infra.cli.Option;
import com.kasouza.lilo.shared.infra.cli.commands.ICommand;
import com.kasouza.lilo.shared.infra.cli.commands.TestCommand;

public class App {
    public static void main(String[] args) {
        ArgParser parse = new ArgParser();

        List<OptDef> optDefs = new ArrayList<>();
        optDefs.add(new OptDef(Option.TEST, "--test", true));

        Arguments arguments = parse.parse(args, optDefs);
        if (arguments == null) {
            return;
        }

        Map<String, ICommand> commands = new HashMap<>();
        commands.put("test", new TestCommand());

        ICommand cmd = commands.get(arguments.getCommandName());
        if (cmd == null) {
            System.err.println("Invalid command " + arguments.getCommandName());
            return;
        }

        cmd.execute(arguments.getCommandArgument(), arguments.getOptions());
    }
}
