package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

/**
 * The STORE instruction
 *
 * @author Tiffany Lee
 */
public class Store implements Instruction{
    /** the variable name */
    private final String name;
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Creates a new instruction
     * @param name the variable name
     * @param machine the machine
     */
    public Store(String name, Maquina machine) {
        this.name = name;
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the value off the top of stack and sets the variable's value
     * in the symbol table to the value.
     */
    @Override
    public void execute(){
        // TODO
        stack.pop();
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString(){
        return Maquina.STORE + " " + this.name;
    }
}
