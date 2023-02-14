package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Maquina;

/**
 * The LOAD instruction
 *
 * @author Tiffany Lee
 */
public class Load implements Instruction {
    /** the variable name */
    private final String name;
    /** the instruction stack */
    private final InstructionStack stack;
    /** the symbol table */
    private final SymbolTable symbolTable;

    /**
     * Creates a new instruction
     * @param name the variable name
     * @param machine the machine
     */
    public Load(String name, Maquina machine) {
        this.name = name;
        this.stack = machine.getInstructionStack();
        this.symbolTable = machine.getSymbolTable();
    }

    /**
     * Run this instruction on the Machine, using the Machine's
     * value stack and symbol table.
     */
    @Override
    public void execute(){
        stack.push(symbolTable.get(name));
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString(){
        return Maquina.LOAD + " " + this.name;
    }
}
