package Parse;

import java.io.FileInputStream;

public class TestLexer {
    public static void main(String[] args) throws Exception {
        ErrorMsg.ErrorMsg errorMsg = new ErrorMsg.ErrorMsg(args[0]);
        FileInputStream inp = new FileInputStream(args[0]);
        Yylex lexer = new Yylex(inp, errorMsg);
        
        java_cup.runtime.Symbol tok;
        while ((tok = lexer.nextToken()).sym != sym.EOF) {
            System.out.println(tok.sym + " " + tok.left + "\t$" + tok.value);
        }
        System.out.println("EOF " + tok.left);
    }
}
