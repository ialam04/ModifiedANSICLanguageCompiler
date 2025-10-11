package Absyn;

// Type cast expressions (e.g., (int)x, (char*)ptr)
public class CastExp extends Exp {
    public Object type;     // the target type (simplified for now)
    public Exp exp;         // the expression to cast
    
    public CastExp(int pos, Object type, Exp exp) {
        super(pos);
        this.type = type;
        this.exp = exp;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}