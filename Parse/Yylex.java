package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 0, 1, 1, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		1, 3, 0, 0, 0, 4, 5, 0,
		6, 7, 8, 9, 10, 11, 12, 13,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 15, 0, 16, 17, 18, 0,
		0, 19, 19, 19, 19, 19, 19, 19,
		19, 19, 19, 19, 19, 19, 19, 19,
		19, 19, 19, 19, 19, 19, 19, 19,
		19, 19, 19, 20, 0, 21, 22, 19,
		0, 23, 24, 25, 26, 27, 28, 29,
		30, 31, 19, 32, 33, 34, 35, 36,
		37, 19, 38, 39, 40, 41, 42, 43,
		44, 45, 46, 0, 47, 0, 48, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 2, 3, 4, 2, 2, 5,
		6, 2, 7, 2, 8, 9, 2, 10,
		11, 12, 13, 2, 2, 14, 15, 2,
		2, 2, 2, 2, 2, 2, 2, 2,
		2, 2, 2, 16, 2, 2, 2, 17,
		2, 18, 13, 2, 2, 2, 2, 13,
		13, 13, 13, 13, 13, 13, 13, 13,
		13, 13, 13, 13, 13, 13, 13, 13,
		13, 13, 13, 13, 13, 13, 13, 13,
		13, 13, 19, 20, 21, 22, 23, 24,
		25, 26, 27, 28, 29, 30, 31, 32,
		33, 34, 35, 36, 37, 38, 39, 40,
		41, 42, 43, 44, 45, 46, 47, 48,
		49, 50, 51, 52, 53, 54, 55, 56,
		57, 58, 59, 60, 61, 62, 63, 64,
		65, 66, 67, 68, 69, 70, 71, 72,
		73, 74, 75, 76, 77, 78, 79, 80,
		81, 82, 83, 84, 85, 86, 87, 88,
		89, 90, 91, 92, 93, 94, 95, 96,
		97, 98, 99, 100, 101, 102, 103, 104,
		105, 106, 107, 108, 109, 110, 111, 112,
		113, 114, 115, 116, 117 
	};
	private int yy_nxt[][] = unpackFromString(118,49,
"-1,1,2,74,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,130,154,131,75,132,104,133,18,76,18,134,18:4,167,155,172,156,135,157,18:3,22,23,-1,1,-1:113,25,-1:36,26,-1:11,27,-1:48,28,-1:40,29,-1:7,30,-1:42,31,-1:5,32,33,-1:47,34,-1:45,13,-1:50,35,36,-1:48,37,-1:48,38,39,-1:44,18,-1:4,18,-1:3,18:24,-1:19,40,-1:48,43,-1:29,44,-1:18,45,-1:48,46,-1:45,18,-1:4,18,-1:3,18:18,142,18:5,-1:19,24,-1:45,18,-1:4,18,-1:3,18:13,41,18:10,-1:16,18,-1:4,18,-1:3,18:5,42,18:6,78,18:11,-1:16,18,-1:4,18,-1:3,18:15,47,18:8,-1:16,18,-1:4,18,-1:3,18:17,48,18:6,-1:16,18,-1:4,18,-1:3,18:13,49,18:10,-1:16,18,-1:4,18,-1:3,18:15,50,18:8,-1:16,18,-1:4,18,-1:3,18:4,51,18:19,-1:16,18,-1:4,18,-1:3,18:11,52,18:12,-1:16,18,-1:4,18,-1:3,18:13,53,18:10,-1:16,18,-1:4,18,-1:3,18:6,54,18:17,-1:16,18,-1:4,18,-1:3,18:3,55,18:20,-1:16,18,-1:4,18,-1:3,18:9,56,18:14,-1:16,18,-1:4,18,-1:3,18:17,57,18:6,-1:16,18,-1:4,18,-1:3,18:17,58,18:6,-1:16,18,-1:4,18,-1:3,18:17,59,18:6,-1:16,18,-1:4,18,-1:3,18:12,60,18:11,-1:16,18,-1:4,18,-1:3,18:4,61,18:19,-1:16,18,-1:4,18,-1:3,18:4,62,18:19,-1:16,18,-1:4,18,-1:3,18:12,63,18:11,-1:16,18,-1:4,18,-1:3,18:12,64,18:11,-1:16,18,-1:4,18,-1:3,18:3,65,18:20,-1:16,18,-1:4,18,-1:3,18:5,66,18:18,-1:16,18,-1:4,18,-1:3,18:2,67,18:21,-1:16,18,-1:4,18,-1:3,18:17,68,18:6,-1:16,18,-1:4,18,-1:3,18:5,69,18:18,-1:16,18,-1:4,18,-1:3,18:4,70,18:19,-1:16,18,-1:4,18,-1:3,18:15,71,18:8,-1:16,18,-1:4,18,-1:3,18:3,72,18:20,-1:16,18,-1:4,18,-1:3,18:4,73,18:19,-1:16,18,-1:4,18,-1:3,18:10,138,18:2,77,18:10,-1:16,18,-1:4,18,-1:3,18:17,79,18:6,-1:16,18,-1:4,18,-1:3,80,18:23,-1:16,18,-1:4,18,-1:3,18:16,81,18:7,-1:16,18,-1:4,18,-1:3,18:18,82,18:5,-1:16,18,-1:4,18,-1:3,18:17,83,18:6,-1:16,18,-1:4,18,-1:3,18:12,84,18:11,-1:16,18,-1:4,18,-1:3,18:8,85,18,171,18:13,-1:16,18,-1:4,18,-1:3,86,18:23,-1:16,18,-1:4,18,-1:3,18:16,87,163,18:6,-1:16,18,-1:4,18,-1:3,88,18:23,-1:16,18,-1:4,18,-1:3,18:15,89,18:8,-1:16,18,-1:4,18,-1:3,18:13,90,18:10,-1:16,18,-1:4,18,-1:3,18:10,91,18:13,-1:16,18,-1:4,18,-1:3,18:10,92,18:13,-1:16,18,-1:4,18,-1:3,18:15,93,18:8,-1:16,18,-1:4,18,-1:3,18:15,94,18:8,-1:16,18,-1:4,18,-1:3,18:4,95,18:19,-1:16,18,-1:4,18,-1:3,18:13,96,18:10,-1:16,18,-1:4,18,-1:3,18:8,97,18:15,-1:16,18,-1:4,18,-1:3,18:2,98,18:21,-1:16,18,-1:4,18,-1:3,18:4,99,18:19,-1:16,18,-1:4,18,-1:3,18:18,100,18:5,-1:16,18,-1:4,18,-1:3,18:4,101,18:19,-1:16,18,-1:4,18,-1:3,18:4,102,18:19,-1:16,18,-1:4,18,-1:3,18:10,103,18:13,-1:16,18,-1:4,18,-1:3,18:18,105,18:5,-1:16,18,-1:4,18,-1:3,18:7,106,18:5,137,18:10,-1:16,18,-1:4,18,-1:3,18:10,107,18,108,18:8,158,18:2,-1:16,18,-1:4,18,-1:3,18:13,109,18:10,-1:16,18,-1:4,18,-1:3,18:13,110,18:10,-1:16,18,-1:4,18,-1:3,18:13,111,18:10,-1:16,18,-1:4,18,-1:3,18:4,112,18:19,-1:16,18,-1:4,18,-1:3,18:12,113,18:11,-1:16,18,-1:4,18,-1:3,18:13,114,18:10,-1:16,18,-1:4,18,-1:3,18:13,115,18:10,-1:16,18,-1:4,18,-1:3,18:8,116,18:7,170,18:7,-1:16,18,-1:4,18,-1:3,18:8,117,18:15,-1:16,18,-1:4,18,-1:3,18,118,18:22,-1:16,18,-1:4,18,-1:3,18:4,119,18:19,-1:16,18,-1:4,18,-1:3,18:18,120,18:5,-1:16,18,-1:4,18,-1:3,18:12,121,18:11,-1:16,18,-1:4,18,-1:3,18:4,122,18:19,-1:16,18,-1:4,18,-1:3,18:17,123,18:6,-1:16,18,-1:4,18,-1:3,18:18,124,18:5,-1:16,18,-1:4,18,-1:3,18:3,125,18:20,-1:16,18,-1:4,18,-1:3,18:12,126,18:11,-1:16,18,-1:4,18,-1:3,18:17,127,18:6,-1:16,18,-1:4,18,-1:3,18:12,128,18:11,-1:16,18,-1:4,18,-1:3,18:8,129,18:15,-1:16,18,-1:4,18,-1:3,18:15,136,18:8,-1:16,18,-1:4,18,-1:3,18:7,139,160,18:8,161,18:6,-1:16,18,-1:4,18,-1:3,18:12,140,18:11,-1:16,18,-1:4,18,-1:3,18:7,141,18:16,-1:16,18,-1:4,18,-1:3,18:17,143,18:6,-1:16,18,-1:4,18,-1:3,18:6,169,18:10,144,18:6,-1:16,18,-1:4,18,-1:3,18:6,145,18:16,146,-1:16,18,-1:4,18,-1:3,147,18:14,148,18:8,-1:16,18,-1:4,18,-1:3,18:4,149,18:19,-1:16,18,-1:4,18,-1:3,18:8,150,18:15,-1:16,18,-1:4,18,-1:3,18:16,151,18:7,-1:16,18,-1:4,18,-1:3,18:6,152,18:17,-1:16,18,-1:4,18,-1:3,18:17,153,18:6,-1:16,18,-1:4,18,-1:3,18:4,159,18:19,-1:16,18,-1:4,18,-1:3,18:14,162,18:9,-1:16,18,-1:4,18,-1:3,18:8,164,18:15,-1:16,18,-1:4,18,-1:3,18:8,165,18:15,-1:16,18,-1:4,18,-1:3,166,18:23,-1:16,18,-1:4,18,-1:3,18:22,168,18,-1:2");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

  return tok(sym.EOF, null);
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{ /* skip whitespace (040 is octal for space) */ }
					case -2:
						break;
					case 2:
						{ newline(); }
					case -3:
						break;
					case 3:
						{return tok(sym.MODULUS, null);}
					case -4:
						break;
					case 4:
						{return tok(sym.BITWISEAND, null);}
					case -5:
						break;
					case 5:
						{return tok(sym.LPAREN, null);}
					case -6:
						break;
					case 6:
						{return tok(sym.RPAREN, null);}
					case -7:
						break;
					case 7:
						{return tok(sym.TIMES, null);}
					case -8:
						break;
					case 8:
						{return tok(sym.PLUS, null);}
					case -9:
						break;
					case 9:
						{return tok(sym.COMMA, null);}
					case -10:
						break;
					case 10:
						{return tok(sym.MINUS, null);}
					case -11:
						break;
					case 11:
						{return tok(sym.PERIOD, null);}
					case -12:
						break;
					case 12:
						{return tok(sym.DIVIDE, null);}
					case -13:
						break;
					case 13:
						{ return tok(sym.INT, Integer.valueOf(yytext())); }
					case -14:
						break;
					case 14:
						{return tok(sym.COLON, null);}
					case -15:
						break;
					case 15:
						{return tok(sym.LT, null);}
					case -16:
						break;
					case 16:
						{return tok(sym.ASSIGN, null);}
					case -17:
						break;
					case 17:
						{return tok(sym.GT, null);}
					case -18:
						break;
					case 18:
						{ return tok(sym.ID, yytext()); }
					case -19:
						break;
					case 19:
						{return tok(sym.LBRACK, null);}
					case -20:
						break;
					case 20:
						{return tok(sym.RBRACK, null);}
					case -21:
						break;
					case 21:
						{return tok(sym.BWISEXOR, null);}
					case -22:
						break;
					case 22:
						{return tok(sym.BWISEOR, null);}
					case -23:
						break;
					case 23:
						{return tok(sym.TILDE, null);}
					case -24:
						break;
					case 24:
						{return tok(sym.NEQ, null);}
					case -25:
						break;
					case 25:
						{return tok(sym.MODASSIGN, null);}
					case -26:
						break;
					case 26:
						{return tok(sym.AND, null);}
					case -27:
						break;
					case 27:
						{return tok(sym.BWISEANDASSIGN, null);}
					case -28:
						break;
					case 28:
						{return tok(sym.MULASSIGN, null);}
					case -29:
						break;
					case 29:
						{return tok(sym.INCREMENT, null);}
					case -30:
						break;
					case 30:
						{return tok(sym.ADDASSIGN, null);}
					case -31:
						break;
					case 31:
						{return tok(sym.DECREMENT, null);}
					case -32:
						break;
					case 32:
						{return tok(sym.SUBASSIGN, null);}
					case -33:
						break;
					case 33:
						{return tok(sym.ARROW, null);}
					case -34:
						break;
					case 34:
						{return tok(sym.DIVASSIGN, null);}
					case -35:
						break;
					case 35:
						{return tok(sym.LSHIFT, null);}
					case -36:
						break;
					case 36:
						{return tok(sym.LE, null);}
					case -37:
						break;
					case 37:
						{return tok(sym.EQ, null);}
					case -38:
						break;
					case 38:
						{return tok(sym.GE, null);}
					case -39:
						break;
					case 39:
						{return tok(sym.RSHIFT, null);}
					case -40:
						break;
					case 40:
						{return tok(sym.BWISEXORASSIGN, null);}
					case -41:
						break;
					case 41:
						{return tok(sym.DO, null);}
					case -42:
						break;
					case 42:
						{return tok(sym.IF, null);}
					case -43:
						break;
					case 43:
						{return tok(sym.BWISEORASSIGN, null);}
					case -44:
						break;
					case 44:
						{return tok(sym.OR, null);}
					case -45:
						break;
					case 45:
						{return tok(sym.LSHIFTASSIGN, null);}
					case -46:
						break;
					case 46:
						{return tok(sym.RSHIFTASSIGN, null);}
					case -47:
						break;
					case 47:
						{return tok(sym.FOR, null);}
					case -48:
						break;
					case 48:
						{return tok(sym.INT, null);}
					case -49:
						break;
					case 49:
						{return tok(sym.AUTO, null);}
					case -50:
						break;
					case 50:
						{return tok(sym.CHAR, null);}
					case -51:
						break;
					case 51:
						{return tok(sym.ELSE, null);}
					case -52:
						break;
					case 52:
						{return tok(sym.ENUM, null);}
					case -53:
						break;
					case 53:
						{return tok(sym.GOTO, null);}
					case -54:
						break;
					case 54:
						{return tok(sym.LONG, null);}
					case -55:
						break;
					case 55:
						{return tok(sym.VOID, null);}
					case -56:
						break;
					case 56:
						{return tok(sym.BREAK, null);}
					case -57:
						break;
					case 57:
						{return tok(sym.CONST, null);}
					case -58:
						break;
					case 58:
						{return tok(sym.FLOAT, null);}
					case -59:
						break;
					case 59:
						{return tok(sym.SHORT, null);}
					case -60:
						break;
					case 60:
						{return tok(sym.UNION, null);}
					case -61:
						break;
					case 61:
						{return tok(sym.WHILE, null);}
					case -62:
						break;
					case 62:
						{return tok(sym.DOUBLE, null);}
					case -63:
						break;
					case 63:
						{return tok(sym.EXTERN, null);}
					case -64:
						break;
					case 64:
						{return tok(sym.RETURN, null);}
					case -65:
						break;
					case 65:
						{return tok(sym.SIGNED, null);}
					case -66:
						break;
					case 66:
						{return tok(sym.SIZEOF, null);}
					case -67:
						break;
					case 67:
						{return tok(sym.STATIC, null);}
					case -68:
						break;
					case 68:
						{return tok(sym.STRUCT, null);}
					case -69:
						break;
					case 69:
						{return tok(sym.TYPEDEF, null);}
					case -70:
						break;
					case 70:
						{return tok(sym.CONTINUE, null);}
					case -71:
						break;
					case 71:
						{return tok(sym.REGISTER, null);}
					case -72:
						break;
					case 72:
						{return tok(sym.UNSIGNED, null);}
					case -73:
						break;
					case 73:
						{return tok(sym.VOLATILE, null);}
					case -74:
						break;
					case 75:
						{ return tok(sym.ID, yytext()); }
					case -75:
						break;
					case 76:
						{ return tok(sym.ID, yytext()); }
					case -76:
						break;
					case 77:
						{ return tok(sym.ID, yytext()); }
					case -77:
						break;
					case 78:
						{ return tok(sym.ID, yytext()); }
					case -78:
						break;
					case 79:
						{ return tok(sym.ID, yytext()); }
					case -79:
						break;
					case 80:
						{ return tok(sym.ID, yytext()); }
					case -80:
						break;
					case 81:
						{ return tok(sym.ID, yytext()); }
					case -81:
						break;
					case 82:
						{ return tok(sym.ID, yytext()); }
					case -82:
						break;
					case 83:
						{ return tok(sym.ID, yytext()); }
					case -83:
						break;
					case 84:
						{ return tok(sym.ID, yytext()); }
					case -84:
						break;
					case 85:
						{ return tok(sym.ID, yytext()); }
					case -85:
						break;
					case 86:
						{ return tok(sym.ID, yytext()); }
					case -86:
						break;
					case 87:
						{ return tok(sym.ID, yytext()); }
					case -87:
						break;
					case 88:
						{ return tok(sym.ID, yytext()); }
					case -88:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -89:
						break;
					case 90:
						{ return tok(sym.ID, yytext()); }
					case -90:
						break;
					case 91:
						{ return tok(sym.ID, yytext()); }
					case -91:
						break;
					case 92:
						{ return tok(sym.ID, yytext()); }
					case -92:
						break;
					case 93:
						{ return tok(sym.ID, yytext()); }
					case -93:
						break;
					case 94:
						{ return tok(sym.ID, yytext()); }
					case -94:
						break;
					case 95:
						{ return tok(sym.ID, yytext()); }
					case -95:
						break;
					case 96:
						{ return tok(sym.ID, yytext()); }
					case -96:
						break;
					case 97:
						{ return tok(sym.ID, yytext()); }
					case -97:
						break;
					case 98:
						{ return tok(sym.ID, yytext()); }
					case -98:
						break;
					case 99:
						{ return tok(sym.ID, yytext()); }
					case -99:
						break;
					case 100:
						{ return tok(sym.ID, yytext()); }
					case -100:
						break;
					case 101:
						{ return tok(sym.ID, yytext()); }
					case -101:
						break;
					case 102:
						{ return tok(sym.ID, yytext()); }
					case -102:
						break;
					case 103:
						{ return tok(sym.ID, yytext()); }
					case -103:
						break;
					case 104:
						{ return tok(sym.ID, yytext()); }
					case -104:
						break;
					case 105:
						{ return tok(sym.ID, yytext()); }
					case -105:
						break;
					case 106:
						{ return tok(sym.ID, yytext()); }
					case -106:
						break;
					case 107:
						{ return tok(sym.ID, yytext()); }
					case -107:
						break;
					case 108:
						{ return tok(sym.ID, yytext()); }
					case -108:
						break;
					case 109:
						{ return tok(sym.ID, yytext()); }
					case -109:
						break;
					case 110:
						{ return tok(sym.ID, yytext()); }
					case -110:
						break;
					case 111:
						{ return tok(sym.ID, yytext()); }
					case -111:
						break;
					case 112:
						{ return tok(sym.ID, yytext()); }
					case -112:
						break;
					case 113:
						{ return tok(sym.ID, yytext()); }
					case -113:
						break;
					case 114:
						{ return tok(sym.ID, yytext()); }
					case -114:
						break;
					case 115:
						{ return tok(sym.ID, yytext()); }
					case -115:
						break;
					case 116:
						{ return tok(sym.ID, yytext()); }
					case -116:
						break;
					case 117:
						{ return tok(sym.ID, yytext()); }
					case -117:
						break;
					case 118:
						{ return tok(sym.ID, yytext()); }
					case -118:
						break;
					case 119:
						{ return tok(sym.ID, yytext()); }
					case -119:
						break;
					case 120:
						{ return tok(sym.ID, yytext()); }
					case -120:
						break;
					case 121:
						{ return tok(sym.ID, yytext()); }
					case -121:
						break;
					case 122:
						{ return tok(sym.ID, yytext()); }
					case -122:
						break;
					case 123:
						{ return tok(sym.ID, yytext()); }
					case -123:
						break;
					case 124:
						{ return tok(sym.ID, yytext()); }
					case -124:
						break;
					case 125:
						{ return tok(sym.ID, yytext()); }
					case -125:
						break;
					case 126:
						{ return tok(sym.ID, yytext()); }
					case -126:
						break;
					case 127:
						{ return tok(sym.ID, yytext()); }
					case -127:
						break;
					case 128:
						{ return tok(sym.ID, yytext()); }
					case -128:
						break;
					case 129:
						{ return tok(sym.ID, yytext()); }
					case -129:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 131:
						{ return tok(sym.ID, yytext()); }
					case -131:
						break;
					case 132:
						{ return tok(sym.ID, yytext()); }
					case -132:
						break;
					case 133:
						{ return tok(sym.ID, yytext()); }
					case -133:
						break;
					case 134:
						{ return tok(sym.ID, yytext()); }
					case -134:
						break;
					case 135:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -136:
						break;
					case 137:
						{ return tok(sym.ID, yytext()); }
					case -137:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 139:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 141:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 143:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 145:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 147:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 148:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 149:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 151:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 153:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -154:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 166:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 167:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
