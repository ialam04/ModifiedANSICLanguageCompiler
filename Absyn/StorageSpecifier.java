package Absyn;

// Represents storage class specifiers in C declarations
public class StorageSpecifier {
    public int pos;
    public int storage;
    
    // Storage class constants
    public static final int TYPEDEF = 0;
    public static final int EXTERN = 1;
    public static final int STATIC = 2;
    public static final int AUTO = 3;
    public static final int REGISTER = 4;
    public static final int CONST = 5;     // Type qualifier
    public static final int VOLATILE = 6;   // Type qualifier
    
    public StorageSpecifier(int pos, int storage) {
        this.pos = pos;
        this.storage = storage;
    }
    
    public String getStorageName() {
        switch (storage) {
            case TYPEDEF: return "typedef";
            case EXTERN: return "extern";
            case STATIC: return "static";
            case AUTO: return "auto";
            case REGISTER: return "register";
            case CONST: return "const";
            case VOLATILE: return "volatile";
            default: return "unknown";
        }
    }
}