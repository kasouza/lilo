package com.kasouza.lilo.shared.lang.ast;

import java.util.List;

public class ProgramNode extends Node {
    private List<StepNode> steps;

    public ProgramNode(List<StepNode> steps) {
        this.steps = steps;
    }
    
    public List<StepNode> getSteps() {
        return steps;
    }

    @Override
    public List<? extends Node> getChildren() {
        return this.steps;
    }
}
