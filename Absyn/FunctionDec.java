package Absyn;

import Symbol.Symbol;

// Function declaration: storage-class return-type name(parameters) [body];
public class FunctionDec extends Dec {
    public SpecifierList specifiers;  // Storage class specifiers
    public TypeSpecifier returnType;  // Return type
    public Symbol name;               // Function name
    public ParameterList params;      // Parameter list
    public Stm body;                  // Function body (null for declarations)
    
    public FunctionDec(int pos, SpecifierList specifiers, TypeSpecifier returnType,
                       Symbol name, ParameterList params, Stm body) {
        this.pos = pos;
        this.specifiers = specifiers;
        this.returnType = returnType;
        this.name = name;
        this.params = params;
        this.body = body;
    }
    
    // Simplified constructor for function declarations without body
    public FunctionDec(int pos, TypeSpecifier returnType, Symbol name, 
                       ParameterList params) {
        this.pos = pos;
        this.specifiers = null;
        this.returnType = returnType;
        this.name = name;
        this.params = params;
        this.body = null;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}