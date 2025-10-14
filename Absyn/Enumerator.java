package Absyn;

public class Enumerator {
    public Symbol.Symbol name;
    public Exp value;  // null if no explicit value
    
    public Enumerator(Symbol.Symbol name, Exp value) {
        this.name = name;
        this.value = value;
    }
}
