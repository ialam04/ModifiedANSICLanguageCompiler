package Absyn;

public class EnumDec extends Dec {
    public int bitfields;  // Combination of bitfield flags
    public Symbol.Symbol name;
    public EnumeratorList enumerators;
    
    public EnumDec(int pos, int bitfields, Symbol.Symbol name, EnumeratorList enumerators) {
        this.pos = pos;
        this.bitfields = bitfields;
        this.name = name;
        this.enumerators = enumerators;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
