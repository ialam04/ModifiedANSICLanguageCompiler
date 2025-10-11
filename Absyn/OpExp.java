package Absyn;

// Binary and unary operation expressions
public class OpExp extends Exp {
    // Binary arithmetic operators
    public final static int PLUS = 0, MINUS = 1, TIMES = 2, DIVIDE = 3, MOD = 4;
    
    // Relational operators
    public final static int LT = 5, LE = 6, GT = 7, GE = 8, EQ = 9, NE = 10;
    
    // Logical operators
    public final static int AND = 11, OR = 12;
    
    // Bitwise operators
    public final static int BITWISE_AND = 13, BITWISE_OR = 14, BITWISE_XOR = 15;
    public final static int LSHIFT = 16, RSHIFT = 17, BITWISE_NOT = 18;
    
    // Unary operators
    public final static int UNARY_MINUS = 19, UNARY_PLUS = 20;
    public final static int PRE_INC = 21, PRE_DEC = 22, POST_INC = 23, POST_DEC = 24;
    public final static int SIZEOF = 25, NOT = 26;
    public final static int ADDRESS = 27, DEREFERENCE = 28;  // & and *
    
    // Special operators
    public final static int COMMA = 29;
    
    public int oper;
    public Exp left, right;  // right can be null for unary operations
    
    public OpExp(int pos, int oper, Exp left, Exp right) {
        super(pos);
        this.oper = oper;
        this.left = left;
        this.right = right;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}