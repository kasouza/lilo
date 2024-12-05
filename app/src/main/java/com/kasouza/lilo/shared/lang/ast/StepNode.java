package com.kasouza.lilo.shared.lang.ast;

import java.util.List;

import com.kasouza.lilo.shared.lang.ast.stmt.StatementNode;

public class StepNode extends Node {
    public List<StatementNode> statements;

    public StepNode(List<StatementNode> statements) {
        this.statements = statements;
    }

    @Override
    public List<? extends Node> getChildren() {
        return this.statements;
    }
}
