package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;
import java.util.List;

public class BinaryOperation implements ExpressionNode{
//    private static final String ADD;
//    private static final String DIV;
//    private static final String MOD;
//    private static final String MULL;
//    private static final String SUB;
//    private static final List<String> OPERATORS;

    private final String operator;
    private final ExpressionNode leftChild;
    private final ExpressionNode rightChild;

    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
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
