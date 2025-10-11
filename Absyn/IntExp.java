package Absyn;

// Integer literal expressions (e.g., 42, 0x1A, 077)
public class IntExp extends Exp {
    public int value;
    
    public IntExp(int pos, int value) {
        super(pos);
        this.value = value;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}