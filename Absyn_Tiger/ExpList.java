package Absyn_Tiger;
import Absyn.Exp;
import Absyn.ExpList;
import Symbol.Symbol;
public class ExpList {
   public Exp head;
   public ExpList tail;
   public ExpList(Exp h, ExpList t) {head=h; tail=t;}
}
