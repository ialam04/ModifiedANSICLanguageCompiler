package Absyn;

public class BreakStm extends Stm {
    public BreakStm(int pos) {
        this.pos = pos;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}