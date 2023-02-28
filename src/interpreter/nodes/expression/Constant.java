package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

/**
 * An ExpressionNode representing a constant, i.e.,
 * a literal integer value
 *
 * @author Tiffany Lee
 */
public class Constant implements ExpressionNode{
    /** the value */
    private final int value;

    /**
     * Creates a new constant
     *
     * @param value the value
     */
    public Constant(int value) {
        this.value = value;
    }

    /** Print the stored value to standard output */
    public void emit(){
        System.out.print(value);
    }

    /**
     * Return the stored value when evaluated
     *
     * @param symTbl the symbol table is ignored here
     * @return the value
     */
    public int evaluate(SymbolTable symTbl){
        return value;
    }

    /**
     * Generates the MAQ instruction for pushing the value
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        out.println("PUSH " + value);
    }
}