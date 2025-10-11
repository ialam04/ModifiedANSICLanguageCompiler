package Absyn;

// Assignment expressions (=, +=, -=, *=, /=, %=, etc.)
public class AssignExp extends Exp {
    // Assignment operator types
    public final static int ASSIGN = 0;           // =
    public final static int PLUS_ASSIGN = 1;     // +=
    public final static int MINUS_ASSIGN = 2;    // -=
    public final static int TIMES_ASSIGN = 3;    // *=
    public final static int DIVIDE_ASSIGN = 4;   // /=
    public final static int MOD_ASSIGN = 5;      // %=
    public final static int LSHIFT_ASSIGN = 6;   // <<=
    public final static int RSHIFT_ASSIGN = 7;   // >>=
    public final static int AND_ASSIGN = 8;      // &=
    public final static int XOR_ASSIGN = 9;      // ^=
    public final static int OR_ASSIGN = 10;      // |=
    
    public int oper;
    public Exp target;    // left-hand side (must be lvalue)
    public Exp value;     // right-hand side
    
    public AssignExp(int pos, int oper, Exp target, Exp value) {
        super(pos);
        this.oper = oper;
        this.target = target;
        this.value = value;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}