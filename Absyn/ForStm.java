package Absyn;

public class ForStm extends Stm {
    public Exp init;        // Initialization expression (can be null)
    public Exp test;        // Test condition expression (can be null)
    public Exp increment;   // Increment expression (can be null)
    public Stm body;        // The loop body statement
    
    public ForStm(int pos, Exp init, Exp test, Exp increment, Stm body) {
        this.pos = pos;
        this.init = init;
        this.test = test;
        this.increment = increment;
        this.body = body;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}