package Absyn;

// Sequence of expressions (e.g., for comma operator: a, b, c)
public class SeqExp extends Exp {
    public ExpList list;
    
    public SeqExp(int pos, ExpList list) {
        super(pos);
        this.list = list;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}