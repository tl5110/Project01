package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

/**
 * The NEGATE instruction
 *
 * @author Tiffany Lee
 */
public class Negate implements Instruction {
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Creates a new instruction
     * @param machine the machine
     */
    public Negate(Maquina machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the operand off the stack, and pushes the result of negating it
     */
    @Override
    public void execute(){
        stack.push(-stack.pop());
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString(){
        // TODO
        return Maquina.NEGATE + " " + this.stack;
    }
}
