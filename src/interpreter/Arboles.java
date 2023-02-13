package interpreter;

import common.Errors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The main program for the high level ARB language.  It takes a program in ARB,
 * converts it into a token list, builds the parse trees for each statement,
 * displays the program in infix, interprets/evaluates the program, then
 * compiles into MAQ instructions so that the machine, Maquina, can execute it.
 *
 * @author RIT CS
 */
public class Arboles {
    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    /** the ARB print token */
    private final static String PRINT = "@";
    /** the ARB assignment token */
    private final static String ASSIGN = "=";

    /** the location to generate the compiled ARB program of MAQ instructions */
    private final static String TMP_MAQ_FILE = "tmp/TEMP.maq";

    // TODO

    /**
     * Create a new Arbelos instance.  The result of this method is the tokenization
     * of the entire ARB input into a list of strings.
     *
     * @param in where to read the ARB input from
     * @param stdin if true, the user should be prompted to enter ARB statements until
     *              a terminating ".".
     */
    public Arboles(Scanner in, boolean stdin) {
        if (stdin) {
            System.out.print("🌳 ");
        }
        // TODO
    }

    /**
     * Build the parse trees into the program which is a list of ActioNode's -
     * one per line of ARB input.
     */
    public void buildProgram() {
        // TODO
    }


    /**
     * Displays the entire ARB program of ActionNode's to standard
     * output using emit().
     */
    public void displayProgram() {
        System.out.println("(ARB) infix...");
        // TODO
    }

    /**
     * Execute the ARB program of ActionNode's to standard output using execute().
     * In order to execute the ActioNodes, a local SymbolTable must be created here
     * for use.
     */
    public void interpretProgram() {
        System.out.println("(ARB) interpreting program...");
        // TODO

        System.out.println("(ARB) Symbol table:");
        // TODO
    }

    /**
     * Compile the ARB program using ActionNode's compile() into the
     * temporary MAQ instruction file.
     *
     * @throws IOException if there are issues working with the temp file
     */
    public void compileProgram() throws IOException {
        System.out.println("(ARB) compiling program to " + TMP_MAQ_FILE + "...");
        PrintWriter out = new PrintWriter(TMP_MAQ_FILE);

        // TODO

        out.close();
    }

    /**
     * Takes the generated MAQ instruction file and assembles/executes
     * it using the Maquina machine.
     *
     * @throws FileNotFoundException if the MAQ file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        // TODO
    }

    /**
     * The main program runs either with no input (ARB program entered through standard
     * input), or with a file name that represents the ARB program.
     *
     * @param args command line arguments
     * @throws IOException if there are issues working with the ARB/MAQ files.
     */
    public static void main(String[] args) throws IOException {
        // determine ARB input source
        Scanner arbIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            arbIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1) {
            arbIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Arbelos filename.arb");
            System.exit(1);
        }

        // step 1: read ARB program into token list
        Arboles interpreter = new Arboles(arbIn, stdin);

        // step 2: parse and build the program from the token list
        interpreter.buildProgram();

        // step 3: display the program in infix
        interpreter.displayProgram();

        // step 4: interpret program
        interpreter.interpretProgram();

        // step 5: compile the program
        interpreter.compileProgram();

        // step 6: have machine execute compiled program
        interpreter.executeProgram();
    }
}
