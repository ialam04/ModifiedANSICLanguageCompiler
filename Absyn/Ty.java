package Absyn;

abstract public class Ty {
    public int pos;
    public abstract void accept(Visitor v);
}
