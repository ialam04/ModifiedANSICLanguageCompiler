package Absyn;

import Symbol.Symbol;

// Struct or Union declaration: struct/union [name] { field-list }
public class StructDec extends Dec {
    public boolean isUnion;       // true for union, false for struct
    public Symbol name;           // Optional tag name
    public FieldDecList fields;   // Field declarations
    
    public StructDec(int pos, boolean isUnion, Symbol name, FieldDecList fields) {
        this.pos = pos;
        this.isUnion = isUnion;
        this.name = name;
        this.fields = fields;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}