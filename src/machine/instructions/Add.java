package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

/**
 * The ADD instruction
 *
 * @author Tiffany Lee
 */
public class Add implements Instruction{
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Create a new instruction.
     * @param machine the machine
     */
    public Add(Maquina machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes
     * the result of the first added by the second.
     */
    @Override
    public void execute() {
        stack.push(stack.pop() + stack.pop());
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Maquina.ADD;
    }
}
