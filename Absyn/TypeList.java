package Absyn;

public class TypeList {
    public Ty head;
    public TypeList tail;
    
    public TypeList(Ty head, TypeList tail) {
        this.head = head;
        this.tail = tail;
    }
}
