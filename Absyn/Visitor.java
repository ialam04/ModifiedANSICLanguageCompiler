package Absyn;

// Simple visitor interface for traversing AST nodes
// Used by Print.java to display the parse tree
public interface Visitor {
    // Expression visitors
    void visit(IntExp exp);
    void visit(CharExp exp);
    void visit(StringExp exp);
    void visit(VarExp exp);
    void visit(OpExp exp);
    void visit(AssignExp exp);
    void visit(CallExp exp);
    void visit(SubscriptExp exp);
    void visit(FieldExp exp);
    void visit(CastExp exp);
    void visit(SeqExp exp);
    
    // Statement and Declaration visitors (for future expansion)
    void visit(Stm stm);
    void visit(Dec dec);
}