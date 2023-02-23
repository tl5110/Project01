package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * The machine can process/execute a series of low level MAQ instructions using
 * an instruction stack and symbol table.
 *
 * @author RIT CS
 * @author Tiffany Lee
 */
public class Maquina {
    /** the push instruction */
    public final static String PUSH = "PUSH";
    /** the print instruction */
    public final static String PRINT = "PRINT";
    /** the store instruction */
    public final static String STORE = "STORE";
    /** the load instruction */
    public final static String LOAD = "LOAD";
    /** the negate instruction */
    public final static String NEGATE = "NEG";
    /** the square root instruction */
    public final static String SQUARE_ROOT = "SQRT";
    /** the add instruction */
    public final static String ADD = "ADD";
    /** the subtract instruction */
    public final static String SUBTRACT = "SUB";
    /** the multiply instruction */
    public final static String MULTIPLY = "MUL";
    /** the divide instruction */
    public final static String DIVIDE = "DIV";
    /** the modulus instruction */
    public final static String MODULUS = "MOD";

    /** the list of valid machine instructions */
    public static final List< String > OPERATIONS =
            List.of(
                    ADD,
                    DIVIDE,
                    LOAD,
                    MODULUS,
                    MULTIPLY,
                    NEGATE,
                    PUSH,
                    PRINT,
                    SQUARE_ROOT,
                    STORE,
                    SUBTRACT
            );

    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";
    /** the table that maps the variable names to their integer values */
    private static SymbolTable symbolTable;
    /** the stack for handling instructions */
    private static InstructionStack instructionStack;
    /** the list of instructions that get assembled */
    private static List<Instruction> instructionsList;

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Maquina() {
        symbolTable = new SymbolTable();
        instructionStack = new InstructionStack();
        instructionsList = new ArrayList<>();
    }

    /**
     * Return the instruction stack.
     *
     * @return the stack
     */
    public InstructionStack getInstructionStack() {
        return instructionStack;
    }

    /**
     * Return the symbol table.
     *
     * @return the symbol table
     */
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }


    /**
     * Assemble the machine instructions.
     *
     * @param maqIn the input source
     * @param stdin true if input is coming from standard input (for prompting)
     */
    public void assemble(Scanner maqIn, boolean stdin) {
        if (stdin) System.out.print("🤖 ");
        loop: while (maqIn.hasNextLine()) {
            if (stdin) System.out.print("🤖 ");
            String[] instruction = maqIn.nextLine().strip().split("\\s+");
            switch (instruction[0]) {
                case PUSH -> {
                    Push push = new Push(Integer.parseInt(instruction[1]), this);
                    instructionsList.add(push);
                } case PRINT -> {
                    Print print = new Print(this);
                    instructionsList.add(print);
                } case STORE -> {
                    Store store = new Store(instruction[1], this);
                    instructionsList.add(store);
                } case LOAD -> {
                    Load load = new Load(instruction[1], this);
                    instructionsList.add(load);
                } case NEGATE -> {
                    Negate neg = new Negate(this);
                    instructionsList.add(neg);
                } case SQUARE_ROOT -> {
                    SquareRoot sqr = new SquareRoot(this);
                    instructionsList.add(sqr);
                } case ADD -> {
                    Add add = new Add(this);
                    instructionsList.add(add);
                } case SUBTRACT -> {
                    Subtract sub = new Subtract(this);
                    instructionsList.add(sub);
                } case MULTIPLY -> {
                    Multiply mul = new Multiply(this);
                    instructionsList.add(mul);
                } case DIVIDE -> {
                    Divide div = new Divide(this);
                    instructionsList.add(div);
                } case MODULUS -> {
                    Modulus mod = new Modulus(this);
                    instructionsList.add(mod);
                } case EOF -> {
                    break loop;
                } default -> Errors.report(Errors.Type.ILLEGAL_INSTRUCTION, instruction[0]);
            }
        }
        System.out.println("(MAQ) Machine instructions:");
        instructionsList.forEach(System.out::println);
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(MAQ) Executing...");
        instructionsList.forEach(Instruction::execute);

        System.out.println("(MAQ) Completed execution!");
        System.out.println("(MAQ) Symbol table:");
        System.out.println(symbolTable.toString() + instructionStack.toString());
    }

    /**
     * The main method.  Machine instructions can either be specified from standard input
     * (no command line), or from a file (only argument on command line).  From
     * here the machine assembles the instructions and then executes them.
     *
     * @param args command line argument (optional)
     * @throws FileNotFoundException if the machine file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // determine input source
        Scanner maqIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            maqIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1){
            maqIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Maquina [filename.maq]");
            System.exit(1);
        }

        Maquina machine = new Maquina();
        machine.assemble(maqIn, stdin);     // assemble the machine instructions
        machine.execute();                  // execute the program
        maqIn.close();
    }
}
