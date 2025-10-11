package Absyn;

// Array subscript expressions (e.g., arr[i], matrix[x][y])
public class SubscriptExp extends Exp {
    public Exp array;    // the array expression
    public Exp index;    // the index expression
    
    public SubscriptExp(int pos, Exp array, Exp index) {
        super(pos);
        this.array = array;
        this.index = index;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}