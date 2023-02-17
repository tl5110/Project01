package machine.instructions;

import common.Errors;
import machine.InstructionStack;
import machine.Maquina;
import java.lang.Math;

/**
 * The SQRT/SquareRoot instruction
 *
 * @author Tiffany Lee
 */
public class SquareRoot implements Instruction{
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Create a new instruction.
     * @param machine the machine
     */
    public SquareRoot(Maquina machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the operand off the stack, and pushes the integer result
     * of taking the square root of it
     */
    @Override
    public void execute() {
        int p = stack.pop();
        if(p < 0){
            Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
        }
        stack.push((int) Math.sqrt(p));
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Maquina.SQUARE_ROOT;
    }
}
