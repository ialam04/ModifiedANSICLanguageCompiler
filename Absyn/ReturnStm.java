package Absyn;

public class ReturnStm extends Stm {
    public Exp exp;    // The return expression (can be null for void returns)
    
    public ReturnStm(int pos, Exp exp) {
        this.pos = pos;
        this.exp = exp;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}