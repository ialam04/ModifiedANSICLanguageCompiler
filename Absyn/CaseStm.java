package Absyn;

public class CaseStm extends Stm {
    public Exp constant;     // The constant expression for this case
    public Stm statement;    // The statement that follows the case
    
    public CaseStm(int pos, Exp constant, Stm statement) {
        this.pos = pos;
        this.constant = constant;
        this.statement = statement;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}