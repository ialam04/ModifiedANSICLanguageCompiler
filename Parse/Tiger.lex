package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
  return tok(sym.EOF, null);
%eofval}


%%
[ \t\r\f]+         { /* skip */ }
\n	{newline();}
","	{return tok(sym.COMMA, null);}

"auto" {return tok(sym.AUTO, null);}
"break" {return tok(sym.BREAK, null);}
"char" {return tok(sym.CHAR, null);}
"const" {return tok(sym.CONST, null);}
"continue" {return tok(sym.CONTINUE, null);}
"do" {return tok(sym.DO, null);}
"double" {return tok(sym.DOUBLE, null);}
"else" {return tok(sym.ELSE, null);}
"enum" {return tok(sym.ENUM, null);}
"extern" {return tok(sym.EXTERN, null);}
"float" {return tok(sym.FLOAT, null);}
"for" {return tok(sym.FOR, null);}
"goto" {return tok(sym.GOTO, null);}
"if" {return tok(sym.IF, null);}
"int" {return tok(sym.INT, null);}
"long" {return tok(sym.LONG, null);}
"register" {return tok(sym.REGISTER, null);}
"return" {return tok(sym.RETURN, null);}
"short" {return tok(sym.SHORT, null);}
"signed" {return tok(sym.SIGNED, null);}
"sizeof" {return tok(sym.SIZEOF, null);}
"static" {return tok(sym.STATIC, null);}
"struct" {return tok(sym.STRUCT, null);}
"typedef" {return tok(sym.TYPEDEF, null);}
"union" {return tok(sym.UNION, null);}
"unsigned" {return tok(sym.UNSIGNED, null);}
"void" {return tok(sym.VOID, null);}
"volatile" {return tok(sym.VOLATILE, null);}
"while" {return tok(sym.WHILE, null);}

[a-zA-Z_][a-zA-Z0-9_]*  { return tok(sym.ID, yytext()); }
[0-9]+     { return tok(sym.INT, Integer.valueOf(yytext())); }

"[" {return tok(sym.LBRACK, null);}
"]" {return tok(sym.RBRACK, null);}
">" {return tok(sym.GT, null);}
"==" {return tok(sym.EQ, null);}

. { err("Illegal character: " + yytext()); }