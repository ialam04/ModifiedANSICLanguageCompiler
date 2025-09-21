package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char
%state COMMENT

%state STRING
%state CHAR

%{

private StringBuffer string = new StringBuffer();

private void appendEscapeSequence(char c) {
  
  switch(c) {
    case '\'': string.append('\''); break;
    case '\"': string.append('\"'); break;
    case '?': string.append('?'); break;
    case '\\': string.append('\\'); break;
    case 'b': string.append('\b'); break;
    case 'f': string.append('\f'); break;
    case 'n': string.append('\n'); break;
    case 'r': string.append('\r'); break;
    case 't': string.append('\t'); break;
    case 'v': string.append('\013'); break;
    default:
      err("Invalid escape sequence: \\" + c);
      string.append(c);
      break;
  }


}

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
<YYINITIAL,COMMENT> \n                  { newline(); }
<YYINITIAL> [\t\r\f\040]+       { /* skip whitespace (040 is octal for space) */ }

<YYINITIAL> "//"[^\r\n]*        { /* skip line comment */ }
<YYINITIAL> "/*"                { yybegin(COMMENT); }

<COMMENT> "*/"                  { yybegin(YYINITIAL); }
<COMMENT> .                     { /* skip comment content */ }

<YYINITIAL>\" {string.setLength(0); yybegin(STRING);}
<YYINITIAL>\' {string.setLength(0); yybegin(CHAR);}

<STRING> [^\"\\\n]+ {string.append(yytext());}

<STRING> \\[0-7][0-7]?[0-7]? {
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}

<STRING> \\[ntrb\"\\fv'?]  { appendEscapeSequence(yytext().charAt(1)); }

<STRING> \\x[0-9A-Fa-f]+ {
  String hexStr = yytext().substring(2);
  int value = Integer.parseInt(hexStr, 16);
  string.append((char)value);
}

<STRING> \\. { 
  err("Invalid escape sequence: " + yytext()); 
  }

<STRING> <<EOF>> {
    err("Unterminated string literal at end of file");
    yybegin(YYINITIAL);
    return tok(sym.EOF, null);
}

<STRING> \" {
    yybegin(YYINITIAL);
    return tok(sym.STRING_LITERAL, string.toString());
  }

<CHAR> [^\'\\\n] {string.append(yytext());}


<CHAR> \\[0-7][0-7]?[0-7]? {
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}

<CHAR> \\[ntrb\"\\fv'?]  { appendEscapeSequence(yytext().charAt(1)); }

<CHAR> \\x[0-9A-Fa-f]+ {
  String hexStr = yytext().substring(2);
  int value = Integer.parseInt(hexStr, 16);
  string.append((char)value);
}


<CHAR> \\. {err("Invalid escape sequence: " + yytext());}

<CHAR> <<EOF>> {
    err("Unterminated character literal at end of file");
    yybegin(YYINITIAL);
    return tok(sym.EOF, null);
}
<CHAR> \' {
    if (string.length() == 0) {
      err("empty character literal");
      yybegin(YYINITIAL);
      return tok(sym.error, null);
    }
    yybegin(YYINITIAL);
    return tok(sym.CHAR_LITERAL, string.toString());
  }

<YYINITIAL> "auto" {return tok(sym.AUTO, null);}
<YYINITIAL> "break" {return tok(sym.BREAK, null);}
<YYINITIAL> "char" {return tok(sym.CHAR, null);}
<YYINITIAL> "const" {return tok(sym.CONST, null);}
<YYINITIAL> "continue" {return tok(sym.CONTINUE, null);}
<YYINITIAL> "do" {return tok(sym.DO, null);}
<YYINITIAL> "double" {return tok(sym.DOUBLE, null);}
<YYINITIAL> "else" {return tok(sym.ELSE, null);}
<YYINITIAL> "enum" {return tok(sym.ENUM, null);}
<YYINITIAL> "extern" {return tok(sym.EXTERN, null);}
<YYINITIAL> "float" {return tok(sym.FLOAT, null);}
<YYINITIAL> "for" {return tok(sym.FOR, null);}
<YYINITIAL> "goto" {return tok(sym.GOTO, null);}
<YYINITIAL> "if" {return tok(sym.IF, null);}
<YYINITIAL> "int" {return tok(sym.INT, null);}
<YYINITIAL> "long" {return tok(sym.LONG, null);}
<YYINITIAL> "register" {return tok(sym.REGISTER, null);}
<YYINITIAL> "return" {return tok(sym.RETURN, null);}
<YYINITIAL> "short" {return tok(sym.SHORT, null);}
<YYINITIAL> "signed" {return tok(sym.SIGNED, null);}
<YYINITIAL> "sizeof" {return tok(sym.SIZEOF, null);}
<YYINITIAL> "static" {return tok(sym.STATIC, null);}
<YYINITIAL> "struct" {return tok(sym.STRUCT, null);}
<YYINITIAL> "typedef" {return tok(sym.TYPEDEF, null);}
<YYINITIAL> "union" {return tok(sym.UNION, null);}
<YYINITIAL> "unsigned" {return tok(sym.UNSIGNED, null);}
<YYINITIAL> "void" {return tok(sym.VOID, null);}
<YYINITIAL> "volatile" {return tok(sym.VOLATILE, null);}
<YYINITIAL> "while" {return tok(sym.WHILE, null);}

<YYINITIAL> [a-zA-Z_][a-zA-Z0-9_]*  { return tok(sym.ID, yytext()); }

<YYINITIAL> 0[xX][0-9a-fA-F]+  { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
<YYINITIAL> 0[0-7]*            { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext(),8)); }
<YYINITIAL> [1-9][0-9]*        { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }

<YYINITIAL> ";" {return tok(sym.SEMICOLON, null);}
<YYINITIAL> "{" {return tok(sym.LBRACE, null);}
<YYINITIAL> "}" {return tok(sym.RBRACE, null);}
<YYINITIAL> "..." {return tok(sym.ELIPSES, null);}
<YYINITIAL> "[" {return tok(sym.LBRACK, null);}
<YYINITIAL> "]" {return tok(sym.RBRACK, null);}
<YYINITIAL> "(" {return tok(sym.LPAREN, null);}
<YYINITIAL> ")" {return tok(sym.RPAREN, null);}
<YYINITIAL> "." {return tok(sym.PERIOD, null);}
<YYINITIAL> "->" {return tok(sym.ARROW, null);}
<YYINITIAL> "++" {return tok(sym.INCREMENT, null);}
<YYINITIAL> "--" {return tok(sym.DECREMENT, null);}
<YYINITIAL> "&" {return tok(sym.BITWISEAND, null);}
<YYINITIAL> "*" {return tok(sym.TIMES, null);}
<YYINITIAL> "+" {return tok(sym.PLUS, null);}
<YYINITIAL> "-" {return tok(sym.MINUS, null);}
<YYINITIAL> "~" {return tok(sym.TILDE, null);}
<YYINITIAL> "/" {return tok(sym.DIVIDE, null);}
<YYINITIAL> "%" {return tok(sym.MODULUS, null);}
<YYINITIAL> "<<" {return tok(sym.LSHIFT, null);}
<YYINITIAL> ">>" {return tok(sym.RSHIFT, null);}
<YYINITIAL> "<" {return tok(sym.LT, null);}
<YYINITIAL> ">" {return tok(sym.GT, null);}
<YYINITIAL> "<=" {return tok(sym.LE, null);}
<YYINITIAL> ">=" {return tok(sym.GE, null);}
<YYINITIAL> "==" {return tok(sym.EQ, null);}
<YYINITIAL> "!=" {return tok(sym.NEQ, null);}
<YYINITIAL> "^" {return tok(sym.BWISEXOR, null);}
<YYINITIAL> "|" {return tok(sym.BWISEOR, null);}
<YYINITIAL> "&&" {return tok(sym.AND, null);}
<YYINITIAL> "||" {return tok(sym.OR, null);}
<YYINITIAL> ":" {return tok(sym.COLON, null);}
<YYINITIAL> "=" {return tok(sym.ASSIGN, null);}
<YYINITIAL> "*=" {return tok(sym.MULASSIGN, null);}
<YYINITIAL> "/=" {return tok(sym.DIVASSIGN, null);}
<YYINITIAL> "%=" {return tok(sym.MODASSIGN, null);}
<YYINITIAL> "+=" {return tok(sym.ADDASSIGN, null);}
<YYINITIAL> "-=" {return tok(sym.SUBASSIGN, null);}
<YYINITIAL> "<<=" {return tok(sym.LSHIFTASSIGN, null);}
<YYINITIAL> ">>=" {return tok(sym.RSHIFTASSIGN, null);}
<YYINITIAL> "&=" {return tok(sym.BWISEANDASSIGN, null);}
<YYINITIAL> "^=" {return tok(sym.BWISEXORASSIGN, null);}
<YYINITIAL> "|=" {return tok(sym.BWISEORASSIGN, null);}
<YYINITIAL> "," {return tok(sym.COMMA, null);}


<YYINITIAL> . { err("Illegal character: " + yytext(); }
