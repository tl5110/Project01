package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

public class Constant implements ExpressionNode{
    private final int value;

    public Constant(int value) {
        this.value = value;
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
