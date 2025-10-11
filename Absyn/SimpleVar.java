package Absyn;

// Simple variable reference (e.g., x, myVariable)
public class SimpleVar extends Var {
    public Symbol.Symbol name;
    
    public SimpleVar(int pos, Symbol.Symbol name) {
        super(pos);
        this.name = name;
    }
}