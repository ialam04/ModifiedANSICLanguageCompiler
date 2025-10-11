package Absyn_Tiger;
import Absyn.Exp;
import Absyn.Var;
import Symbol.Symbol;
public class VarExp extends Exp {
   public Var var;
   public VarExp(int p, Var v) {pos=p; var=v;}
}   
