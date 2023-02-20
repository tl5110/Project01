package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import java.io.PrintWriter;

public class Assignment implements ActionNode {
    private final String name;
    private final ExpressionNode child;

    public Assignment(String name, ExpressionNode child) {
        this.name = name;
        this.child = child;
    }

    public void emit(){
        // TODO
    }

    public void execute(SymbolTable symbol){
        // TODO
    }

    public void compile(PrintWriter out){
        // TODO
    }
}
