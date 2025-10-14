package Absyn;

public class EnumeratorList {
    public Enumerator head;
    public EnumeratorList tail;
    
    public EnumeratorList(Enumerator head, EnumeratorList tail) {
        this.head = head;
        this.tail = tail;
    }
}
