package Absyn;

// Character literal expressions (e.g., 'a', '\n', '\0')
public class CharExp extends Exp {
    public char value;
    
    public CharExp(int pos, char value) {
        super(pos);
        this.value = value;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}