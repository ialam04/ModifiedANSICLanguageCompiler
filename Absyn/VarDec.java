package Absyn;

public class VarDec extends Dec {
    public int bitfields;  // Combination of bitfield flags
    public Ty type;
    public Symbol.Symbol name;
    public Exp init;       // Optional initializer
    
    public VarDec(int pos, int bitfields, Ty type, Symbol.Symbol name, Exp init) {
        this.pos = pos;
        this.bitfields = bitfields;
        this.type = type;
        this.name = name;
        this.init = init;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    // Bitfield constants
    public static final int CONST = 1;
    public static final int VOLATILE = 2;
    public static final int EXTERN = 4;
    public static final int STATIC = 8;
    public static final int AUTO = 16;
    public static final int REGISTER = 32;
}
