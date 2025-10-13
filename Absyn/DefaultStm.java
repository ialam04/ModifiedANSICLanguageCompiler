package Absyn;

public class DefaultStm extends Stm {
    public Stm statement;    // The statement that follows the default label
    
    public DefaultStm(int pos, Stm statement) {
        this.pos = pos;
        this.statement = statement;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}