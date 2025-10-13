package Absyn;

// List of storage/type qualifiers
public class SpecifierList {
    public StorageSpecifier head;
    public SpecifierList tail;
    
    public SpecifierList(StorageSpecifier head, SpecifierList tail) {
        this.head = head;
        this.tail = tail;
    }
}