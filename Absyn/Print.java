package Absyn;
import java.io.PrintWriter;

public class Print implements Visitor {
    private PrintWriter out;
    private int indent = 0;
    
    public Print(PrintWriter writer) {
        out = writer;
    }
    
    // Main entry point for printing expressions
    public void prExp(Exp exp, int d) {
        indent = d;
        if (exp != null) {
            exp.accept(this);
        } else {
            out.print("null");
        }
    }
    
    // Main entry point for printing statements
    public void prStm(Stm stm, int d) {
        indent = d;
        if (stm != null) {
            stm.accept(this);
        } else {
            out.print("null");
        }
    }
    
    // Helper method for indentation
    private void indent() {
        for (int i = 0; i < indent; i++) {
            out.print(" ");
        }
    }
    
    // Print a variable
    private void prVar(Var var) {
        if (var instanceof SimpleVar) {
            SimpleVar sv = (SimpleVar) var;
            out.print(sv.name.toString());
        } else if (var instanceof FieldVar) {
            FieldVar fv = (FieldVar) var;
            out.print("(");
            prExp(fv.record, 0);
            out.print(".");  // FieldVar always uses dot
            out.print(fv.field.toString());
            out.print(")");
        } else if (var instanceof SubscriptVar) {
            SubscriptVar sv = (SubscriptVar) var;
            out.print("(");
            prExp(sv.array, 0);
            out.print("[");
            prExp(sv.index, 0);
            out.print("]");
            out.print(")");
        }
    }
    
    // Print expression list
    private void prExpList(ExpList list) {
        if (list == null) return;
        
        prExp(list.head, 0);
        if (list.tail != null) {
            out.print(", ");
            prExpList(list.tail);
        }
    }
    
    // Get operator string
    private String getOpString(int op) {
        switch (op) {
            case OpExp.PLUS: return "+";
            case OpExp.MINUS: return "-";
            case OpExp.TIMES: return "*";
            case OpExp.DIVIDE: return "/";
            case OpExp.MOD: return "%";
            case OpExp.LT: return "<";
            case OpExp.LE: return "<=";
            case OpExp.GT: return ">";
            case OpExp.GE: return ">=";
            case OpExp.EQ: return "==";
            case OpExp.NE: return "!=";
            case OpExp.AND: return "&&";
            case OpExp.OR: return "||";
            case OpExp.BITWISE_AND: return "&";
            case OpExp.BITWISE_OR: return "|";
            case OpExp.BITWISE_XOR: return "^";
            case OpExp.LSHIFT: return "<<";
            case OpExp.RSHIFT: return ">>";
            case OpExp.BITWISE_NOT: return "~";
            case OpExp.UNARY_MINUS: return "-";
            case OpExp.UNARY_PLUS: return "+";
            case OpExp.PRE_INC: return "++";
            case OpExp.PRE_DEC: return "--";
            case OpExp.POST_INC: return "++";
            case OpExp.POST_DEC: return "--";
            case OpExp.SIZEOF: return "sizeof";
            case OpExp.ADDRESS: return "&";
            case OpExp.DEREFERENCE: return "*";
            case OpExp.COMMA: return ",";
            default: return "op" + op;
        }
    }
    
    // Get assignment operator string
    private String getAssignString(int op) {
        switch (op) {
            case AssignExp.ASSIGN: return "=";
            case AssignExp.PLUS_ASSIGN: return "+=";
            case AssignExp.MINUS_ASSIGN: return "-=";
            case AssignExp.TIMES_ASSIGN: return "*=";
            case AssignExp.DIVIDE_ASSIGN: return "/=";
            case AssignExp.MOD_ASSIGN: return "%=";
            case AssignExp.LSHIFT_ASSIGN: return "<<=";
            case AssignExp.RSHIFT_ASSIGN: return ">>=";
            case AssignExp.AND_ASSIGN: return "&=";
            case AssignExp.XOR_ASSIGN: return "^=";
            case AssignExp.OR_ASSIGN: return "|=";
            default: return "assign" + op;
        }
    }
    
    // Visitor methods for each expression type
    public void visit(IntExp exp) {
        out.print(exp.value);
    }
    
    public void visit(CharExp exp) {
        out.print("'" + exp.value + "'");
    }
    
    public void visit(StringExp exp) {
        out.print("\"" + exp.value + "\"");
    }
    
    public void visit(VarExp exp) {
        prVar(exp.var);
    }
    
    public void visit(OpExp exp) {
        if (exp.right == null) {
            // Unary operator
            if (exp.oper == OpExp.POST_INC || exp.oper == OpExp.POST_DEC) {
                out.print("(");
                prExp(exp.left, 0);
                out.print(getOpString(exp.oper));
                out.print(")");
            } else {
                out.print("(");
                out.print(getOpString(exp.oper));
                prExp(exp.left, 0);
                out.print(")");
            }
        } else {
            // Binary operator
            out.print("(");
            prExp(exp.left, 0);
            out.print(" " + getOpString(exp.oper) + " ");
            prExp(exp.right, 0);
            out.print(")");
        }
    }
    
    public void visit(AssignExp exp) {
        out.print("(");
        prExp(exp.target, 0);
        out.print(" " + getAssignString(exp.oper) + " ");
        prExp(exp.value, 0);
        out.print(")");
    }
    
    public void visit(CallExp exp) {
        prExp(exp.func, 0);
        out.print("(");
        if (exp.args != null) {
            prExpList(exp.args);
        }
        out.print(")");
    }
    
    public void visit(SubscriptExp exp) {
        prExp(exp.array, 0);
        out.print("[");
        prExp(exp.index, 0);
        out.print("]");
    }
    
    public void visit(FieldExp exp) {
        prExp(exp.record, 0);
        out.print(exp.isPointer ? "->" : ".");
        out.print(exp.field.toString());
    }
    
    public void visit(CastExp exp) {
        out.print("((cast)");
        prExp(exp.exp, 0);
        out.print(")");
    }
    
    public void visit(SeqExp exp) {
        out.print("(");
        if (exp.list != null) {
            prExpList(exp.list);
        }
        out.print(")");
    }
    
    // Placeholder visitor methods for statements and declarations
    // Your groupmates can implement these when adding statement/declaration parsing
    public void visit(Stm stm) {
        out.print("(Statement)");
    }
    
    public void visit(Dec dec) {
        out.print("(Declaration)");
    }
    
    public void visit(DecList decList) {
        out.print("(program");
        for (DecList d = decList; d != null; d = d.tail) {
            out.println();
            indent += 2;
            indent();
            if (d.head != null) {
                d.head.accept(this);
            }
            indent -= 2;
        }
        out.print(")");
    }
    
    // Specific statement visitor implementations
    public void visit(IfStm stm) {
        out.print("(if ");
        prExp(stm.test, 0);
        out.println();
        indent += 2;
        indent();
        out.print("then ");
        if (stm.thenpart != null) {
            stm.thenpart.accept(this);
        } else {
            out.print("null");
        }
        if (stm.elsepart != null) {
            out.println();
            indent();
            out.print("else ");
            stm.elsepart.accept(this);
        }
        indent -= 2;
        out.print(")");
    }
    
    public void visit(WhileStm stm) {
        out.print("(while ");
        prExp(stm.test, 0);
        out.println();
        indent += 2;
        indent();
        if (stm.body != null) {
            stm.body.accept(this);
        } else {
            out.print("null");
        }
        indent -= 2;
        out.print(")");
    }
    
    public void visit(DoWhileStm stm) {
        out.print("(do ");
        out.println();
        indent += 2;
        indent();
        if (stm.body != null) {
            stm.body.accept(this);
        } else {
            out.print("null");
        }
        out.println();
        indent();
        out.print("while ");
        prExp(stm.test, 0);
        indent -= 2;
        out.print(")");
    }
    
    public void visit(ForStm stm) {
        out.print("(for ");
        if (stm.init != null) {
            prExp(stm.init, 0);
        } else {
            out.print("null");
        }
        out.print("; ");
        if (stm.test != null) {
            prExp(stm.test, 0);
        } else {
            out.print("null");
        }
        out.print("; ");
        if (stm.increment != null) {
            prExp(stm.increment, 0);
        } else {
            out.print("null");
        }
        out.println();
        indent += 2;
        indent();
        if (stm.body != null) {
            stm.body.accept(this);
        } else {
            out.print("null");
        }
        indent -= 2;
        out.print(")");
    }
    
    public void visit(CompoundStm stm) {
        out.print("(compound");
        out.println();
        indent += 2;
        
        if (stm.declarations != null) {
            indent();
            out.print("declarations: ");
            printDecList(stm.declarations);
            out.println();
        }
        
        if (stm.statements != null) {
            indent();
            out.print("statements: ");
            printStmList(stm.statements);
        }
        
        indent -= 2;
        out.print(")");
    }
    
    public void visit(ExpressionStm stm) {
        out.print("(expr-stmt ");
        if (stm.exp != null) {
            prExp(stm.exp, 0);
        } else {
            out.print("empty");
        }
        out.print(")");
    }
    
    public void visit(GotoStm stm) {
        out.print("(goto ");
        out.print(stm.label.toString());
        out.print(")");
    }
    
    public void visit(ContinueStm stm) {
        out.print("(continue)");
    }
    
    public void visit(BreakStm stm) {
        out.print("(break)");
    }
    
    public void visit(ReturnStm stm) {
        out.print("(return ");
        if (stm.exp != null) {
            prExp(stm.exp, 0);
        } else {
            out.print("void");
        }
        out.print(")");
    }
    
    public void visit(LabeledStm stm) {
        out.print("(label ");
        out.print(stm.label.toString());
        out.print(": ");
        if (stm.statement != null) {
            stm.statement.accept(this);
        } else {
            out.print("null");
        }
        out.print(")");
    }
    
    public void visit(CaseStm stm) {
        out.print("(case ");
        prExp(stm.constant, 0);
        out.print(": ");
        if (stm.statement != null) {
            stm.statement.accept(this);
        } else {
            out.print("null");
        }
        out.print(")");
    }
    
    public void visit(DefaultStm stm) {
        out.print("(default: ");
        if (stm.statement != null) {
            stm.statement.accept(this);
        } else {
            out.print("null");
        }
        out.print(")");
    }
    
    // Helper methods for printing lists
    private void printStmList(StmList stmList) {
        out.print("(");
        while (stmList != null) {
            if (stmList.head != null) {
                stmList.head.accept(this);
            }
            stmList = stmList.tail;
            if (stmList != null) {
                out.print(" ");
            }
        }
        out.print(")");
    }
    
    private void printDecList(DecList decList) {
        out.print("(");
        while (decList != null) {
            if (decList.head != null) {
                decList.head.accept(this);
            }
            decList = decList.tail;
            if (decList != null) {
                out.print(" ");
            }
        }
        out.print(")");
    }
    
    // Declaration visitor methods
    public void visit(VarDec dec) {
        out.print("(var-dec ");
        printBitfields(dec.bitfields);
        if (dec.type != null) {
            dec.type.accept(this);
        }
        out.print(" " + dec.name);
        if (dec.init != null) {
            out.print(" = ");
            prExp(dec.init, 0);
        }
        out.print(")");
    }
    
    public void visit(FunctionDec dec) {
        out.print("(function-dec ");
        printBitfields(dec.bitfields);
        if (dec.returnType != null) {
            dec.returnType.accept(this);
        }
        out.print(" " + dec.name);
        out.print("(");
        printFieldList(dec.params);
        if (dec.hasVarArgs) {
            out.print(", ...");
        }
        out.print(")");
        if (dec.body != null) {
            out.println();
            indent += 2;
            indent();
            dec.body.accept(this);
            indent -= 2;
        }
        out.print(")");
    }
    
    public void visit(TypedefDec dec) {
        out.print("(typedef ");
        if (dec.type != null) {
            dec.type.accept(this);
        }
        out.print(" " + dec.name + ")");
    }
    
    public void visit(EnumDec dec) {
        out.print("(enum-dec ");
        printBitfields(dec.bitfields);
        out.print(dec.name + " ");
        printEnumeratorList(dec.enumerators);
        out.print(")");
    }
    
    public void visit(StructDec dec) {
        out.print("(struct-dec ");
        printBitfields(dec.bitfields);
        out.print(dec.name + " ");
        printFieldList(dec.fields);
        out.print(")");
    }
    
    public void visit(UnionDec dec) {
        out.print("(union-dec ");
        printBitfields(dec.bitfields);
        out.print(dec.name + " ");
        printFieldList(dec.fields);
        out.print(")");
    }
    
    public void visit(NameTy ty) {
        out.print("(");
        // Print pointer modifiers
        for (int i = 0; i < ty.pointerDepth; i++) {
            out.print("*");
        }
        out.print(ty.name);
        // Print array dimensions
        if (ty.arrayDimensions != null) {
            printArrayDimensions(ty.arrayDimensions);
        }
        out.print(")");
    }
    
    // Helper methods
    private void printBitfields(int bitfields) {
        if (bitfields != 0) {
            out.print("[");
            if ((bitfields & VarDec.CONST) != 0) out.print("const ");
            if ((bitfields & VarDec.VOLATILE) != 0) out.print("volatile ");
            if ((bitfields & VarDec.EXTERN) != 0) out.print("extern ");
            if ((bitfields & VarDec.STATIC) != 0) out.print("static ");
            if ((bitfields & VarDec.AUTO) != 0) out.print("auto ");
            if ((bitfields & VarDec.REGISTER) != 0) out.print("register ");
            out.print("] ");
        }
    }
    
    private void printFieldList(FieldList fields) {
        out.print("(");
        while (fields != null) {
            if (fields.type != null) {
                fields.type.accept(this);
            }
            if (fields.name != null) {
                out.print(" " + fields.name);
            }
            fields = fields.tail;
            if (fields != null) {
                out.print(", ");
            }
        }
        out.print(")");
    }
    
    private void printEnumeratorList(EnumeratorList enums) {
        out.print("(");
        while (enums != null) {
            out.print(enums.head.name);
            if (enums.head.value != null) {
                out.print(" = ");
                prExp(enums.head.value, 0);
            }
            enums = enums.tail;
            if (enums != null) {
                out.print(", ");
            }
        }
        out.print(")");
    }
    
    private void printArrayDimensions(ExpList dimensions) {
        while (dimensions != null) {
            out.print("[");
            if (dimensions.head != null) {
                prExp(dimensions.head, 0);
            }
            out.print("]");
            dimensions = dimensions.tail;
        }
    }
}
