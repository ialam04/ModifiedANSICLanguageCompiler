package Absyn_Tiger;
import Absyn.FieldList;
import Absyn.Ty;
import Symbol.Symbol;
public class RecordTy extends Ty {
   public FieldList fields;
   public RecordTy(int p, FieldList f) {pos=p; fields=f;}
}   
