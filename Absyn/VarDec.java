package Absyn;

import Symbol.Symbol;

// Variable declaration: storage-class type declarator [= initializer];
public class VarDec extends Dec {
    public SpecifierList specifiers;  // Storage class and type qualifiers
    public TypeSpecifier type;        // Type specifier (int, char, struct, etc.)
    public Declarator declarator;     // Declarator (name, pointers, arrays)
    public Exp initializer;           // Optional initializer expression
    
    public VarDec(int pos, SpecifierList specifiers, TypeSpecifier type, 
                  Declarator declarator, Exp initializer) {
        this.pos = pos;
        this.specifiers = specifiers;
        this.type = type;
        this.declarator = declarator;
        this.initializer = initializer;
    }
    
    // Simplified constructor
    public VarDec(int pos, TypeSpecifier type, Symbol name, Exp initializer) {
        this.pos = pos;
        this.specifiers = null;
        this.type = type;
        this.declarator = new Declarator(pos, name);
        this.initializer = initializer;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}