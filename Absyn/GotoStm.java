package Absyn;
import Symbol.Symbol;

public class GotoStm extends Stm {
    public Symbol label;    // The label to jump to
    
    public GotoStm(int pos, Symbol label) {
        this.pos = pos;
        this.label = label;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}