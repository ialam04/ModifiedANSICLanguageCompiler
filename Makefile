JFLAGS=-g

Parse/*.class: Parse/*.java Parse/Grm.java
	javac ${JFLAGS} Parse/*.java

Parse/Grm.java: Parse/Grm.cup
	cd Parse; java java_cup.Main -parser Grm -expect 6 -nonterms -dump_grammar -dump_states -compact_red <Grm.cup >Grm.out 2>Grm.err

Parse/Yylex.java: Parse/Tiger.lex
	cd Parse; java JLex.Main Tiger.lex; mv Tiger.lex.java Yylex.java
	
clean:
	rm -f */*.class Parse/Grm.java Parse/Grm.err Parse/Grm.out Parse/Yylex.java
