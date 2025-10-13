package Absyn;

import Symbol.Symbol;

// Typedef declaration: typedef type declarator;
public class TypedefDec extends Dec {
    public TypeSpecifier type;        // Base type
    public Declarator declarator;     // Typedef name and type modifiers
    
    public TypedefDec(int pos, TypeSpecifier type, Declarator declarator) {
        this.pos = pos;
        this.type = type;
        this.declarator = declarator;
    }
    
    // Simplified constructor
    public TypedefDec(int pos, TypeSpecifier type, Symbol name) {
        this.pos = pos;
        this.type = type;
        this.declarator = new Declarator(pos, name);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}