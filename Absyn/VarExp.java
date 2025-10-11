package Absyn;

// Variable expression - wraps any Var in an expression
public class VarExp extends Exp {
    public Var var;
    
    public VarExp(int pos, Var var) {
        super(pos);
        this.var = var;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}