package Absyn;

public class CompoundStm extends Stm {
    public DecList declarations;    // List of declarations (can be null)
    public StmList statements;      // List of statements (can be null)
    
    public CompoundStm(int pos, DecList declarations, StmList statements) {
        this.pos = pos;
        this.declarations = declarations;
        this.statements = statements;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}