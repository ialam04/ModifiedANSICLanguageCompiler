package Absyn_Tiger;
import Absyn.Dec;
import Absyn.DecList;
import Symbol.Symbol;
public class DecList {
   public Dec head;
   public DecList tail;
   public DecList(Dec h, DecList t) {head=h; tail=t;}
}
