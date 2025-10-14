package Absyn;

public class NameTy extends Ty {
    public Symbol.Symbol name;
    public int pointerDepth;  // Number of * modifiers
    public ExpList arrayDimensions;  // null for non-arrays, expressions for dimensions
    
    public NameTy(int pos, Symbol.Symbol name, int pointerDepth, ExpList arrayDimensions) {
        this.pos = pos;
        this.name = name;
        this.pointerDepth = pointerDepth;
        this.arrayDimensions = arrayDimensions;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    // Type name constants (for built-in types)
    public static final String VOID = "void";
    public static final String CHAR = "char";
    public static final String SHORT = "short";
    public static final String INT = "int";
    public static final String LONG = "long";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
}
