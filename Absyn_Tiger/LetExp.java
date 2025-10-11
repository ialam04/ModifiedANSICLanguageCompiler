package Absyn_Tiger;
import Absyn.DecList;
import Absyn.Exp;
import Symbol.Symbol;
public class LetExp extends Exp {
   public DecList decs;
   public Exp body;
   public LetExp(int p, DecList d, Exp b) {pos=p; decs=d; body=b;}
}
