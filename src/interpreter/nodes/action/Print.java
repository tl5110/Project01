package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import java.io.PrintWriter;

public class Print implements ActionNode{
    private final ExpressionNode child;

    public Print(ExpressionNode child) {
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
