package Absyn;

// Represents array specifications [size1][size2]...
public class ArraySpecList {
    public Exp size;            // Array size expression (null for [])
    public ArraySpecList tail;  // Next array dimension
    
    public ArraySpecList(Exp size, ArraySpecList tail) {
        this.size = size;
        this.tail = tail;
    }
}