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
    
    // Specific statement visitors
    void visit(IfStm stm);
    void visit(WhileStm stm);
    void visit(DoWhileStm stm);
    void visit(ForStm stm);
    void visit(CompoundStm stm);
    void visit(ExpressionStm stm);
    void visit(GotoStm stm);
    void visit(ContinueStm stm);
    void visit(BreakStm stm);
    void visit(ReturnStm stm);
    void visit(LabeledStm stm);
    void visit(CaseStm stm);
    void visit(DefaultStm stm);
    
    // Declaration visitors
    void visit(VarDec dec);
    void visit(FunctionDec dec);
    void visit(TypedefDec dec);
    void visit(EnumDec dec);
    void visit(StructDec dec);
    void visit(UnionDec dec);
    
    // Type visitors
    void visit(NameTy ty);
}
