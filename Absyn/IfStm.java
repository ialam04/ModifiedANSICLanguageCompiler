package Absyn;

public class IfStm extends Stm {
    public Exp test;        // The condition expression
    public Stm thenpart;    // The statement to execute if true
    public Stm elsepart;    // The statement to execute if false (can be null)
    
    public IfStm(int pos, Exp test, Stm thenpart, Stm elsepart) {
        this.pos = pos;
        this.test = test;
        this.thenpart = thenpart;
        this.elsepart = elsepart;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}