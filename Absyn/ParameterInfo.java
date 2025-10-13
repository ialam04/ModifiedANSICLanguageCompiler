package Absyn;

import Symbol.Symbol;

// Temporary class to hold parameter information during parsing
public class ParameterInfo {
    public TypeSpecifier type;
    public Symbol name;
    
    public ParameterInfo(TypeSpecifier type, Symbol name) {
        this.type = type;
        this.name = name;
    }
}