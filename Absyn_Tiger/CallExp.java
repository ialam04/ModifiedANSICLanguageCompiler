package Absyn_Tiger;
import Absyn.Exp;
import Absyn.ExpList;
import Symbol.Symbol;
public class CallExp extends Exp {
   public Symbol func;
   public ExpList args;
   public CallExp(int p, Symbol f, ExpList a) {pos=p; func=f; args=a;}
}
