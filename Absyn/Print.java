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
}