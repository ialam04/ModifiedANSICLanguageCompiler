package Absyn_Tiger;
import Absyn.Var;
import Symbol.Symbol;
public class SimpleVar extends Var {
   public Symbol name;
   public SimpleVar (int p, Symbol n) {pos=p; name=n;}
}
