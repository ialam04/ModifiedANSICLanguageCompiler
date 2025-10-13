package Absyn;

// List of initializers for braced initialization
public class InitializerList {
    public Initializer head;
    public InitializerList tail;
    
    public InitializerList(Initializer head, InitializerList tail) {
        this.head = head;
        this.tail = tail;
    }
}