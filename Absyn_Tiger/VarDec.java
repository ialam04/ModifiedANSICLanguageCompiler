package Absyn_Tiger;
import Absyn.Dec;
import Absyn.Exp;
import Absyn.NameTy;
import Symbol.Symbol;
public class VarDec extends Dec {
   public Symbol name;
   public boolean escape = true;
   public NameTy typ; /* optional */
   public Exp init;
   public VarDec(int p, Symbol n, NameTy t, Exp i) {pos=p; name=n; typ=t; init=i;}
}
