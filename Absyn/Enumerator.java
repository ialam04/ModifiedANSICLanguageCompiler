package Absyn;

import Symbol.Symbol;

// Enumerator: identifier [= constant-expression]
public class Enumerator {
    public int pos;
    public Symbol name;
    public Exp value;  // Optional constant expression
    
    public Enumerator(int pos, Symbol name, Exp value) {
        this.pos = pos;
        this.name = name;
        this.value = value;
    }
    
    public Enumerator(int pos, Symbol name) {
        this.pos = pos;
        this.name = name;
        this.value = null;
    }
}