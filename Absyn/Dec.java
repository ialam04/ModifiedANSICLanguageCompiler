package Absyn;

abstract public class Dec {
  public int pos;
  public abstract void accept(Visitor v);
}