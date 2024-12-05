package com.kasouza.lilo.shared.lang.ast.stmt;

import java.util.List;

import com.kasouza.lilo.shared.lang.ast.Node;
import com.kasouza.lilo.shared.lang.ast.VariableNode;

public class FunctionStatementNode extends StatementNode {
    public String functionName;
    public List<VariableNode> variables;

    public FunctionStatementNode(String functionName, List<VariableNode> variables) {
        this.functionName = functionName;
        this.variables = variables;
    }

    @Override
    public List<? extends Node> getChildren() {
        return variables;
    }
}
