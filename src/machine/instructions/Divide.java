package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

/**
 * The DIVIDE instruction
 *
 * @author Tiffany Lee
 */
public class Divide implements Instruction{
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Create a new instruction.
     * @param machine the machine
     */
    public Divide(Maquina machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then the first operands off the stack, and pushes
     * the result of the first divided by the second.
     */
    @Override
    public void execute() {
        // TODO
        stack.push(stack.pop() / stack.pop());
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Maquina.DIVIDE;
    }
}