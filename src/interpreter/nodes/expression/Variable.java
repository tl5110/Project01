package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

public class Variable implements ExpressionNode{
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public void emit(){
        // TODO
    }

    public int evaluate(SymbolTable symTbl){
        // TODO
        return 0;
    }

    public void compile(PrintWriter out){
        // TODO
    }
}
