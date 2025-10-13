package Absyn;

import Symbol.Symbol;

// Represents function parameter list
public class ParameterList {
    public TypeSpecifier type;
    public Declarator declarator; // Can be null for abstract parameters
    public Symbol name; // Parameter name (alternative to full declarator)
    public ParameterList tail;
    
    // Constructor for named parameter with full type and declarator
    public ParameterList(TypeSpecifier type, Declarator declarator, ParameterList tail) {
        this.type = type;
        this.declarator = declarator;
        this.name = declarator != null ? declarator.name : null;
        this.tail = tail;
    }
    
    // Constructor for simple named parameter
    public ParameterList(TypeSpecifier type, Symbol name, ParameterList tail) {
        this.type = type;
        this.declarator = null;
        this.name = name;
        this.tail = tail;
    }
}