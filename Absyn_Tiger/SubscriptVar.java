package Absyn_Tiger;
import Absyn.Exp;
import Absyn.Var;
import Symbol.Symbol;
public class SubscriptVar extends Var {
   public Var var;
   public Exp index;
   public SubscriptVar(int p, Var v, Exp i) {pos=p; var=v; index=i;}
}
