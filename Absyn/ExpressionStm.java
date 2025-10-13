package Absyn;

public class ExpressionStm extends Stm {
    public Exp exp;    // The expression to evaluate (can be null for empty statements)
    
    public ExpressionStm(int pos, Exp exp) {
        this.pos = pos;
        this.exp = exp;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}