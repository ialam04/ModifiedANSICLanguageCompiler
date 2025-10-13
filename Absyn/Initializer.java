package Absyn;

// Represents initializers in C declarations
public abstract class Initializer {
    public int pos;
}

// Simple expression initializer
class ExpressionInitializer extends Initializer {
    public Exp exp;
    
    public ExpressionInitializer(int pos, Exp exp) {
        this.pos = pos;
        this.exp = exp;
    }
}

// Braced initializer list
class BracedInitializer extends Initializer {
    public InitializerList list;
    
    public BracedInitializer(int pos, InitializerList list) {
        this.pos = pos;
        this.list = list;
    }
}