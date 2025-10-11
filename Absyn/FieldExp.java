package Absyn;

// Field access expressions (e.g., s.field, ptr->member)
public class FieldExp extends Exp {
    public Exp record;              // the struct/union expression
    public Symbol.Symbol field;     // the field name
    public boolean isPointer;       // true for ->, false for .
    
    public FieldExp(int pos, Exp record, Symbol.Symbol field, boolean isPointer) {
        super(pos);
        this.record = record;
        this.field = field;
        this.isPointer = isPointer;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}