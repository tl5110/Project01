package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import java.io.PrintWriter;
import java.util.List;
import java.lang.Math;

/**
 * A calculation represented by a unary operator and its operand
 *
 * @author Tiffany Lee
 */
public class UnaryOperation implements ExpressionNode{
    /** ARB negation operator */
    private static final String NEG = "!";
    /** ARB square root operator */
    private static final String SQRT  = "$";
    /** the legal unary operators, for use when parsing */
    private static final List<String> OPERATORS =  List.of(NEG, SQRT);

    /** the operator */
    private final String operator;
    /** the child expression */
    private final ExpressionNode child;

    /**
     * Create a new UnaryOperation node
     *
     * @param operator the operator
     * @param child the child expression
     */
    public UnaryOperation(String operator, ExpressionNode child) {
        this.operator = operator;
        this.child = child;
    }

    /**
     * Print to standard output the infix display of the child nodes
     * preceded by the operator and without an intervening blank
     */
    public void emit(){
        System.out.print(operator);
        child.emit();
    }

    /**
     * Compute the result of evaluation the expression and applying the
     * operator to it
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the computation
     */
    public int evaluate(SymbolTable symTbl){
        if(operator.equals(NEG)){
            return -child.evaluate(symTbl);
        } else if(operator.equals(SQRT)){
            if(child.evaluate(symTbl) < 0){
                Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
            }
            return (int) Math.sqrt(child.evaluate(symTbl));
        }
        return child.evaluate(symTbl);
    }

    /**
     * Generates the MAQ instructions for this operation
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        if(operator.equals(NEG)){
            child.compile(out);
            out.println("NEG");
        } else if(operator.equals(SQRT)){
            child.compile(out);
            out.println("SQRT");
        } else {
            Errors.report(Errors.Type.ILLEGAL_OPERATOR, operator);
        }
    }
}
