package Absyn;

// String literal expressions (e.g., "hello", "world\n")
public class StringExp extends Exp {
    public String value;
    
    public StringExp(int pos, String value) {
        super(pos);
        this.value = value;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}