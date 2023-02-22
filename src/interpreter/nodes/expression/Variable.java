package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;
import machine.Maquina;

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
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return this variable's current value in the symbol table
     */
    public int evaluate(SymbolTable symTbl){
        return symTbl.get(name);
    }

    /**
     * Generates the MAQ instruction for loading the variable name
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        out.println(Maquina.LOAD + " " + name);
    }
}
