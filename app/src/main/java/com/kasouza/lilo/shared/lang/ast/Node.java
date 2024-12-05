package com.kasouza.lilo.shared.lang.ast;

import java.util.List;

public abstract class Node {
    public abstract List<? extends Node> getChildren();

    public String toString() {
        return toString("");
    }

    public String toString(String indent) {
        String str = indent + getClass().toString() + "\n";

        List<? extends Node> children = getChildren();

        if (children != null) {
            for (var child : getChildren()) {
                if (child != null) {
                    str += child.toString(indent + "    ");
                }
            }

            System.out.println();
        }

        return str;
    }
}
