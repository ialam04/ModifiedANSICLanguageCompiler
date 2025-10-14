package Absyn;

public class FieldList {
    public Ty type;
    public Symbol.Symbol name;
    public FieldList tail;
    
    public FieldList(Ty type, Symbol.Symbol name, FieldList tail) {
        this.type = type;
        this.name = name;
        this.tail = tail;
    }
}
