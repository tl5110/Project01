package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import java.io.PrintWriter;

/**
 * A node that represents the displaying of the value of an expression
 *
 * @author Tiffany Lee
 */
public class Print implements ActionNode{
    /** the child expression */
    private final ExpressionNode child;

    /**
     * Creates a new Print node
     *
     * @param child the child expression
     */
    public Print(ExpressionNode child) {
        this.child = child;
    }

    /**
     * Prints teh statement to standard output in the format "Print"
     * followed by the infix form of the expression
     */
    public void emit(){
        System.out.print("Print ");
        child.emit();
    }

    /**
     * Evaluates the child expression and prints the result to
     * standard output
     *
     * @param symbol the table where variable values are stored
     */
    public void execute(SymbolTable symbol){
        System.out.println(child.evaluate(symbol));
    }

    /**
     * Generates the MAQ instructions that when instructed will perform
     * the print action
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        child.compile(out);
        out.println("PRINT");
    }
}
