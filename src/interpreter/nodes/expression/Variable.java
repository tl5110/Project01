package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import java.io.PrintWriter;

/**
 * The ExpressionNode for a simple variable
 *
 * @author Tiffany Lee
 */
public class Variable implements ExpressionNode{
    /** the name */
    private final String name;

    /**
     * Creates a new Variable for the identifier
     *
     * @param name the name of this variable
     */
    public Variable(String name) {
        this.name = name;
    }

    /** Print to standard output the name of the Variable */
    public void emit(){
        System.out.print(name);
    }

    /**
     * Get the value of the variable name from the symbol table
     *
     * @param symTbl the table containing all variable values
     * @return this variable's current value in the symbol table
     */
    public int evaluate(SymbolTable symTbl){
        if(!symTbl.has(name)){
            Errors.report(Errors.Type.UNINITIALIZED, name);
        }
        return symTbl.get(name);
    }

    /**
     * Generates the MAQ instruction for loading the variable name
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        out.println("LOAD " + name);
    }
}
