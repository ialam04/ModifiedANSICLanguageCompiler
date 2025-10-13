package Absyn;
import Symbol.Symbol;

public class LabeledStm extends Stm {
    public Symbol label;    // The label name
    public Stm statement;          // The statement that follows the label
    
    public LabeledStm(int pos, Symbol label, Stm statement) {
        this.pos = pos;
        this.label = label;
        this.statement = statement;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}