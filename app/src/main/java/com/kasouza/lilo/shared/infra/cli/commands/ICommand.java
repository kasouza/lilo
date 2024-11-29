package com.kasouza.lilo.shared.infra.cli.commands;

import java.util.Map;

import com.kasouza.lilo.shared.infra.cli.Option;

public interface ICommand {
    public void execute(String arg, Map<Option, String> options);
}
