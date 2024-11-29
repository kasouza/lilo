package com.kasouza.lilo.shared.infra.cli.commands;

import java.util.Map;

import com.kasouza.lilo.shared.infra.cli.Option;

public class TestCommand implements ICommand {
    @Override
    public void execute(String arg, Map<Option, String> options) {
        System.out.printf("test(%s)\n", arg);

        for (var entry : options.entrySet()) {
            System.out.printf(" - %s %s", entry.getKey(), entry.getValue());
        }
    }
}
