package Absyn;

// Array subscript variable (e.g., arr[i], matrix[x][y])
public class SubscriptVar extends Var {
    public Exp array;    // the array expression
    public Exp index;    // the index expression
    
    public SubscriptVar(int pos, Exp array, Exp index) {
        super(pos);
        this.array = array;
        this.index = index;
    }
}