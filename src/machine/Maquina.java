package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; //ADDED for Arrays.toString() in assemble


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

    private final SymbolTable symbolTable;
    private final InstructionStack instructionStack;
    private final List<Instruction> instructionsList;

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Maquina() {
        this.symbolTable = new SymbolTable();
        this.instructionStack = new InstructionStack();
        this.instructionsList = new ArrayList<>();
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
        if (stdin) {
            System.out.print("🤖 ");
        }
        while (maqIn.hasNext()) {
            String[] instruction = maqIn.nextLine().strip().split("\\s+");
            switch (instruction[0]) {
                case PUSH -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Push push = new Push(Integer.parseInt(instruction[1]), this);
                    instructionsList.add(push);
                } case PRINT -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Print print = new Print(this);
                    instructionsList.add(print);
                } case STORE -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Store store = new Store(instruction[1], this);
                    instructionsList.add(store);
                } case LOAD -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Load load = new Load(instruction[1], this);
                    instructionsList.add(load);
                } case NEGATE -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Negate neg = new Negate(this);
                    instructionsList.add(neg);
                } case SQUARE_ROOT -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    SquareRoot sqr = new SquareRoot(this);
                    instructionsList.add(sqr);
                } case ADD -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Add add = new Add(this);
                    instructionsList.add(add);
                } case SUBTRACT -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Subtract sub = new Subtract(this);
                    instructionsList.add(sub);
                } case MULTIPLY -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Multiply mul = new Multiply(this);
                    instructionsList.add(mul);
                } case DIVIDE -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Divide div = new Divide(this);
                    instructionsList.add(div);
                } case MODULUS -> {
                    symbolTable.set(instruction[0], Integer.parseInt(instruction[1]));
                    Modulus mod = new Modulus(this);
                    instructionsList.add(mod);
                }
            }
        }
        System.out.println("(MAQ) Machine instructions:");
        instructionsList.forEach(System.out::println);
        // TODO
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(MAQ) Executing...");

//        for(Instruction instruction : instructionsList){
//            switch (instruction) {
//                case PUSH:
//                    Push(instruction, );
//                    break;
//                case PRINT:
//
//                    break;
//                case STORE:
//
//                    break;
//                case LOAD:
//
//                    break;
//                case NEGATE:
//
//                    break;
//                case SQUARE_ROOT:
//
//                    break;
//                case ADD:
//
//                    break;
//                case SUBTRACT:
//
//                    break;
//                case MULTIPLY:
//
//                    break;
//                case DIVIDE:
//
//                    break;
//                case MODULUS:
//
//                    break;
//            }
//        }

        System.out.println("(MAQ) Completed execution!");
        System.out.println("(MAQ) Symbol table:");

        // TODO
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
