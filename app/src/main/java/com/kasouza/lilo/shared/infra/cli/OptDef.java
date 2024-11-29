package com.kasouza.lilo.shared.infra.cli;

public class OptDef {
    private Option option;
    private String longName = null;
    private String shortName = null;
    private boolean hasArgs = false;

    public OptDef(Option option, String longName) {
        this.option = option;
        this.longName = longName;
    }

    public OptDef(Option option, String longName, Boolean hasArgs) {
        this.option = option;
        this.longName = longName;
        this.hasArgs = hasArgs;
    }

    public OptDef(Option option, String longName, String shortName, boolean hasArgs) {
        this.option = option;
        this.longName = longName;
        this.shortName = shortName;
        this.hasArgs = hasArgs;
    }

    public Option getOption() {
        return option;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean hasArgs() {
        return hasArgs;
    }
}
