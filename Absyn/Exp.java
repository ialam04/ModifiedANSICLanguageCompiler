package Absyn;

public abstract class Exp extends Absyn {
    public Exp() {
        super();
    }
    
    public Exp(int pos) {
        super(pos);
    }
    
    public abstract void accept(Visitor visitor);
}