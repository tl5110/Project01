package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

public class UnaryOperation implements ExpressionNode{
//    private static final String NEG;
//    private static final String SQRT;
//    private static final List<String> OPERATORS;

    private final String operator;
    private final ExpressionNode child;

    public UnaryOperation(String operator, ExpressionNode child) {
        this.operator = operator;
        this.child = child;
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
