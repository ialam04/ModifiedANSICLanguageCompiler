package Absyn;

abstract public class Stm {
  public int pos;
  public abstract void accept(Visitor v);
}