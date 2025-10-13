package Absyn;

public class ContinueStm extends Stm {
    public ContinueStm(int pos) {
        this.pos = pos;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}