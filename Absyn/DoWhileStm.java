package Absyn;

public class DoWhileStm extends Stm {
    public Stm body;    // The loop body statement
    public Exp test;    // The condition expression (checked after body)
    
    public DoWhileStm(int pos, Stm body, Exp test) {
        this.pos = pos;
        this.body = body;
        this.test = test;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}