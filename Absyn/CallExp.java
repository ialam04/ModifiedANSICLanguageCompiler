package Absyn;

// Function call expressions (e.g., func(), printf("hello"), max(a, b))
public class CallExp extends Exp {
    public Exp func;        // function expression
    public ExpList args;    // argument list (can be null for no args)
    
    public CallExp(int pos, Exp func, ExpList args) {
        super(pos);
        this.func = func;
        this.args = args;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}