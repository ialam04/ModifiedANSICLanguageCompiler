package Absyn;

// List of struct/union field declarations
public class FieldDecList {
    public FieldDec head;
    public FieldDecList tail;
    
    public FieldDecList(FieldDec head, FieldDecList tail) {
        this.head = head;
        this.tail = tail;
    }
}