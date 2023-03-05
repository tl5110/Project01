package interpreter;

import common.Errors;
import common.SymbolTable;
import interpreter.nodes.ArbolesNode;
import interpreter.nodes.action.ActionNode;
import interpreter.nodes.action.Assignment;
import interpreter.nodes.expression.*;
import machine.Maquina;
import machine.instructions.Push;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import interpreter.nodes.action.*;



/**
 * The main program for the high level ARB language.  It takes a program in ARB,
 * converts it into a token list, builds the parse trees for each statement,
 * displays the program in infix, interprets/evaluates the program, then
 * compiles into MAQ instructions so that the machine, Maquina, can execute it.
 *
 * @author RIT CS
 * @author Tiffany Lee
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
    /** the list of tokens  */
    private static final List<String> tokenList = new ArrayList<>();
    /** the list of Action nodes */
    private static final List<ActionNode> actionList = new ArrayList<>();

    /**
     * Create a new Arboles instance.  The result of this method is the tokenization
     * of the entire ARB input into a list of strings.
     *
     * @param in where to read the ARB input from
     * @param stdin if true, the user should be prompted to enter ARB statements until
     *              a terminating ".".
     */
    public Arboles(Scanner in, boolean stdin) {
        if (stdin) System.out.print("🌳 ");
        System.out.println("(ARB) prefix...");
        while (in.hasNextLine()){ //&& !in.equals(EOF)
            String token = in.nextLine();
            System.out.println(token);
            tokenList.addAll(List.of(token.strip().split("\\s+")));
        }
    }

    /**
     * A recursive helper function that parses enough of the inputted token list
     * to get the fully represented expression and returns the root as an
     * Expression node
     *
     * @param tokenList the list of tokens
     * @return an Expression Node
     */
    public ExpressionNode helpBuild(List<String> tokenList) {
        if(tokenList.size() == 0){
            Errors.report(Errors.Type.PREMATURE_END);

        } else if (UnaryOperation.OPERATORS.contains(tokenList.get(0))) { //UnaryOperation
            return new UnaryOperation(tokenList.remove(0), helpBuild(tokenList));

        } else if (BinaryOperation.OPERATORS.contains(tokenList.get(0))) { //BinaryOperation
            return new BinaryOperation(tokenList.remove(0), helpBuild(tokenList), helpBuild(tokenList));

        } else if (tokenList.get(0).matches("^[a-zA-Z].*")) { //Variable
            return new Variable(tokenList.remove(0));

        } else if(tokenList.get(0).matches("^-?[0-9].*")){ //Constant
            int value = Integer.parseInt(tokenList.remove(0));
            return new Constant(value);

        } else {
            Errors.report(Errors.Type.ILLEGAL_OPERATOR, tokenList.get(0));
        }
        return null;
    }

    /**
     * Build the parse trees into the program which is a list of ActionNode's -
     * one per line of ARB input.
     */
    public void buildProgram() {
        while(!tokenList.isEmpty()) {
            if (tokenList.get(0).equals(ASSIGN)) {
                tokenList.remove(0);
                String name = tokenList.remove(0);
                Assignment assign = new Assignment(name, helpBuild(tokenList));
                actionList.add(assign);

            } else if (tokenList.get(0).equals(PRINT)) {
                tokenList.remove(0);
                Print print = new Print(helpBuild(tokenList));
                actionList.add(print);

            } else {
                Errors.report(Errors.Type.ILLEGAL_ACTION, tokenList.get(0));
            }
        }
    }

    /**
     * Displays the entire ARB program of ActionNode's to standard
     * output using emit().
     */
    public void displayProgram() {
        System.out.println("(ARB) infix...");
        actionList.forEach(actionNode -> {
            actionNode.emit();
            System.out.print("\n");
        });
    }

    /**
     * Execute the ARB program of ActionNode's to standard output using execute().
     * In order to execute the ActionNodes, a local SymbolTable must be created here
     * for use.
     */
    public void interpretProgram() {
        SymbolTable symbolTable = new SymbolTable();
        System.out.println("(ARB) interpreting program...");
        actionList.forEach(actionNode -> actionNode.execute(symbolTable));
        System.out.println("(ARB) Symbol table:");
        System.out.print(symbolTable);
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
        actionList.forEach(actionNode -> actionNode.compile(out));
        out.close();
    }

    /**
     * Takes the generated MAQ instruction file and assembles/executes
     * it using the Maquina machine.
     *
     * @throws FileNotFoundException if the MAQ file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        Maquina maquina = new Maquina();
        maquina.assemble(new Scanner(new File(TMP_MAQ_FILE)), false);
        maquina.execute();
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
