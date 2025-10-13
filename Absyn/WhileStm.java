package Absyn;

public class WhileStm extends Stm {
    public Exp test;    // The condition expression
    public Stm body;    // The loop body statement
    
    public WhileStm(int pos, Exp test, Stm body) {
        this.pos = pos;
        this.test = test;
        this.body = body;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}