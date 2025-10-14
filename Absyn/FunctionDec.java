package Absyn;

public class FunctionDec extends Dec {
    public int bitfields;  // Combination of bitfield flags
    public Ty returnType;
    public Symbol.Symbol name;
    public FieldList params;
    public boolean hasVarArgs;  // true if has ... parameter
    public Stm body;  // null for function declarations (prototypes)
    
    public FunctionDec(int pos, int bitfields, Ty returnType, Symbol.Symbol name, 
                      FieldList params, boolean hasVarArgs, Stm body) {
        this.pos = pos;
        this.bitfields = bitfields;
        this.returnType = returnType;
        this.name = name;
        this.params = params;
        this.hasVarArgs = hasVarArgs;
        this.body = body;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
