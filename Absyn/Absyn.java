package Absyn;

public abstract class Absyn {
    public int pos;
    
    public Absyn() {
        pos = 0;
    }
    
    public Absyn(int pos) {
        this.pos = pos;
    }
}