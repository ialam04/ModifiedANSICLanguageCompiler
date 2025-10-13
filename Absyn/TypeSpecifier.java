package Absyn;

// Represents a type specifier in C declarations
// Supports basic types, structs, unions, enums, and typedef names
public class TypeSpecifier {
    public int pos;
    public int type;  // Type constant
    public String name; // For struct/union/enum/typedef names
    public Dec structDecl; // For struct/union declarations
    public Dec enumDecl; // For enum declarations
    
    // Type constants
    public static final int VOID = 0;
    public static final int CHAR = 1;
    public static final int SHORT = 2;
    public static final int INT = 3;
    public static final int LONG = 4;
    public static final int FLOAT = 5;
    public static final int DOUBLE = 6;
    public static final int SIGNED = 7;
    public static final int UNSIGNED = 8;
    public static final int STRUCT = 9;
    public static final int UNION = 10;
    public static final int ENUM = 11;
    public static final int TYPEDEF_NAME = 12;
    
    // Constructor for basic types
    public TypeSpecifier(int pos, int type) {
        this.pos = pos;
        this.type = type;
        this.name = null;
        this.structDecl = null;
        this.enumDecl = null;
    }
    
    // Constructor for named types (struct/union/enum/typedef)
    public TypeSpecifier(int pos, int type, String name) {
        this.pos = pos;
        this.type = type;
        this.name = name;
        this.structDecl = null;
        this.enumDecl = null;
    }
    
    // Constructor for struct/union/enum with declaration
    public TypeSpecifier(int pos, int type, String name, Dec declaration, boolean isStruct) {
        this.pos = pos;
        this.type = type;
        this.name = name;
        if (isStruct) {
            this.structDecl = declaration;
            this.enumDecl = null;
        } else {
            this.structDecl = null;
            this.enumDecl = declaration;
        }
    }
    
    public String getTypeName() {
        switch (type) {
            case VOID: return "void";
            case CHAR: return "char";
            case SHORT: return "short";
            case INT: return "int";
            case LONG: return "long";
            case FLOAT: return "float";
            case DOUBLE: return "double";
            case SIGNED: return "signed";
            case UNSIGNED: return "unsigned";
            case STRUCT: return "struct" + (name != null ? " " + name : "");
            case UNION: return "union" + (name != null ? " " + name : "");
            case ENUM: return "enum" + (name != null ? " " + name : "");
            case TYPEDEF_NAME: return name;
            default: return "unknown";
        }
    }
}