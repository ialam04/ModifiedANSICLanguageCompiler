package Absyn;

public class TypedefDec extends Dec {
    public Ty type;
    public Symbol.Symbol name;
    
    public TypedefDec(int pos, Ty type, Symbol.Symbol name) {
        this.pos = pos;
        this.type = type;
        this.name = name;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
