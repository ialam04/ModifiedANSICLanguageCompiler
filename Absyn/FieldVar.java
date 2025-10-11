package Absyn;

// Struct/union field access variable (e.g., s.field, ptr->member)
public class FieldVar extends Var {
    public Exp record;              // the struct/union expression
    public Symbol.Symbol field;     // the field name
    
    public FieldVar(int pos, Exp record, Symbol.Symbol field) {
        super(pos);
        this.record = record;
        this.field = field;
    }
}