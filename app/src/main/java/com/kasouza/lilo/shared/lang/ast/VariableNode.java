package com.kasouza.lilo.shared.lang.ast;

import java.util.List;

public class VariableNode extends Node {
    public String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public List<? extends Node> getChildren() {
        return null;
    }
}
