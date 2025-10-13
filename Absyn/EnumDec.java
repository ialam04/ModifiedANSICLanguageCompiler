package Absyn;

import Symbol.Symbol;

// Enum declaration: enum [name] { enumerator-list }
public class EnumDec extends Dec {
    public Symbol name;               // Optional tag name
    public EnumeratorList enumerators; // List of enumerators
    
    public EnumDec(int pos, Symbol name, EnumeratorList enumerators) {
        this.pos = pos;
        this.name = name;
        this.enumerators = enumerators;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}