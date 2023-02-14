package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

/**
 * The PRINT instruction
 *
 * @author Tiffany Lee
 */
public class Print implements Instruction {
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Creates a new instruction
     * @param machine the machine
     */
    public Print(Maquina machine){
        this.stack = machine.getInstructionStack();
    }

    /** Pops the top operand off the stack and prints the resulting value. */
    @Override
    public void execute(){
        System.out.println(stack.pop());
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString(){
        return Maquina.PRINT;
    }
}
