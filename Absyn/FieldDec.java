package Absyn;

import Symbol.Symbol;

// Struct field declaration
public class FieldDec {
    public int pos;
    public TypeSpecifier type;
    public Symbol name;
    public Exp bitfield; // For bitfield declarations (: constant-expression)
    
    public FieldDec(int pos, TypeSpecifier type, Symbol name, Exp bitfield) {
        this.pos = pos;
        this.type = type;
        this.name = name;
        this.bitfield = bitfield;
    }
    
    public FieldDec(int pos, TypeSpecifier type, Symbol name) {
        this.pos = pos;
        this.type = type;
        this.name = name;
        this.bitfield = null;
    }
}