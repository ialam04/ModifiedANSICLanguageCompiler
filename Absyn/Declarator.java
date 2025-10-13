package Absyn;

import Symbol.Symbol;

// Represents declarators in C declarations (pointers, arrays, functions)
public class Declarator {
    public int pos;
    public Symbol name;         // The declared identifier
    public int pointerCount;    // Number of pointer indirections
    public ArraySpecList arrays; // Array dimensions
    public ParameterList params; // Function parameters (if function declarator)
    
    public Declarator(int pos, Symbol name) {
        this.pos = pos;
        this.name = name;
        this.pointerCount = 0;
        this.arrays = null;
        this.params = null;
    }
    
    public Declarator(int pos, Symbol name, int pointerCount) {
        this.pos = pos;
        this.name = name;
        this.pointerCount = pointerCount;
        this.arrays = null;
        this.params = null;
    }
    
    public Declarator(int pos, Symbol name, int pointerCount, ArraySpecList arrays) {
        this.pos = pos;
        this.name = name;
        this.pointerCount = pointerCount;
        this.arrays = arrays;
        this.params = null;
    }
    
    public Declarator(int pos, Symbol name, int pointerCount, ParameterList params) {
        this.pos = pos;
        this.name = name;
        this.pointerCount = pointerCount;
        this.arrays = null;
        this.params = params;
    }
}