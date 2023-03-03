package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import java.io.PrintWriter;
import java.util.List;

/**
 * A calculation represented by a binary operator and its two operands
 *
 * @author Tiffany Lee
 */
public class BinaryOperation implements ExpressionNode{
    /** ARB addition operator */
    private static final String ADD = "+";
    /** ARB division operator */
    private static final String DIV = "/";
    /** ARB modulus operator */
    private static final String MOD = "%";
    /** ARB multiply operator */
    private static final String MUL = "*";
    /** ARB subtraction operator */
    private static final String SUB = "-";
    /** the legal binary operators, for use when parsing */
    public static final List<String> OPERATORS =
            List.of(ADD, DIV, MOD, MUL, SUB);

    /** the operator */
    private final String operator;
    /** the left child expression */
    private final ExpressionNode leftChild;
    /** the right child expression */
    private final ExpressionNode rightChild;

    /**
     * Creates a new BinaryOperation Node
     *
     * @param operator the operator
     * @param leftChild the left child expression
     * @param rightChild the right child expression
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Print to standard output the infix display of the two child nodes
     * separated by the operator and surrounded by parentheses
     */
    public void emit(){
        System.out.print("( ");
        leftChild.emit();
        System.out.print(" " + operator + " ");
        rightChild.emit();
        System.out.print(" )");
    }

    /**
     * Compute the result of evaluating the child expression and applying
     * the operator to it
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the computation
     */
    public int evaluate(SymbolTable symTbl){
        int left = leftChild.evaluate(symTbl);
        int right = rightChild.evaluate(symTbl);
        return switch (operator) {
            case ADD -> left + right;
            case DIV -> {
                if(right == 0){
                    Errors.report(Errors.Type.DIVIDE_BY_ZERO);
                }
                yield left / right;
            }
            case MOD -> left % right;
            case MUL -> left * right;
            case SUB -> left - right;
            default -> left;
        };
    }

    /**
     * Generates the MAQ instructions for this operation
     *
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        switch (operator) {
            case ADD -> {
                leftChild.compile(out);
                rightChild.compile(out);
                out.println("ADD");
            } case DIV -> {
                leftChild.compile(out);
                rightChild.compile(out);
                out.println("DIV");
            } case MOD -> {
                leftChild.compile(out);
                rightChild.compile(out);
                out.println("MOD");
            } case MUL -> {
                leftChild.compile(out);
                rightChild.compile(out);
                out.println("MUL");
            } case SUB -> {
                leftChild.compile(out);
                rightChild.compile(out);
                out.println("SUB");
            }
        }
    }
}
