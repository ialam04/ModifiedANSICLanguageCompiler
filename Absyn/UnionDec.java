package Absyn;

public class UnionDec extends Dec {
    public int bitfields;  // Combination of bitfield flags
    public Symbol.Symbol name;
    public FieldList fields;
    
    public UnionDec(int pos, int bitfields, Symbol.Symbol name, FieldList fields) {
        this.pos = pos;
        this.bitfields = bitfields;
        this.name = name;
        this.fields = fields;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
