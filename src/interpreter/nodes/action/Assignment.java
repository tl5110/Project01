package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import java.io.PrintWriter;

/**
 * An ActionNode that represents the assignment of the value of
 * an expression to a variable
 *
 * @author Tiffany Lee
 */
public class Assignment implements ActionNode {
    /** the name of the variable */
    private final String name;
    /** the child expression */
    private final ExpressionNode child;

    /**
     * Creates a new Assignment node with an identifier name and child
     * expression
     *
     * @param name the name fo the variable that is getting a new value
     * @param child the expression on the right-hand-side (RHS) of the
     *              assignment statement
     */
    public Assignment(String name, ExpressionNode child) {
        this.name = name;
        this.child = child;
    }

    /**
     * Print to standard output the assignment with the variable name,
     * followed by the assignment token, and followed by the infix form
     * of the child expression
     */
    public void emit(){
        System.out.print(name + " = ");
        child.emit();
    }

    /**
     * Evaluates the child expression and assigns the result to the variable
     *
     * @param symbol the table where variable values are stored
     */
    public void execute(SymbolTable symbol){
        symbol.set(name, child.evaluate(symbol));
    }

    /**
     * Generates the MAQ instructions that when instructed will perform
     * the assignment
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        child.compile(out);
        out.println("STORE " + name);
    }
}
