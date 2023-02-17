package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;
import common.Errors;

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
        int p2 = stack.pop();
        int p1 = stack.pop();
        if(p2 == 0){
            Errors.report(Errors.Type.DIVIDE_BY_ZERO);
        }
        stack.push(p1/p2);
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
