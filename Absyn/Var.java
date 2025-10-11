package Absyn;

// Abstract base class for all variable references
public abstract class Var extends Absyn {
    public Var() {
        super();
    }
    
    public Var(int pos) {
        super(pos);
    }
}