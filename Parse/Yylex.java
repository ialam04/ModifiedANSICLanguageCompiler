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
	private final int STRING = 2;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int CHAR = 3;
	private final int yy_state_dtrans[] = {
		0,
		111,
		113,
		117
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
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
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
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 0, 1, 1, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		1, 3, 4, 0, 0, 5, 6, 7,
		8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 17, 17, 17, 17, 17, 17,
		18, 18, 19, 20, 21, 22, 23, 24,
		0, 25, 25, 25, 25, 26, 27, 28,
		28, 28, 28, 28, 28, 28, 28, 29,
		28, 28, 28, 28, 28, 28, 28, 28,
		30, 28, 28, 31, 32, 33, 34, 28,
		0, 35, 36, 37, 38, 39, 40, 41,
		42, 43, 28, 44, 45, 46, 47, 48,
		49, 28, 50, 51, 52, 53, 54, 55,
		56, 57, 58, 59, 60, 61, 62, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 2, 2, 3, 4, 2, 2,
		2, 5, 6, 2, 7, 8, 9, 10,
		11, 2, 2, 12, 13, 14, 15, 2,
		2, 16, 2, 17, 2, 2, 2, 2,
		2, 2, 2, 2, 2, 2, 2, 2,
		2, 2, 18, 2, 2, 2, 19, 2,
		20, 15, 2, 2, 2, 21, 2, 2,
		15, 15, 15, 15, 15, 15, 15, 15,
		15, 15, 15, 15, 15, 15, 15, 15,
		15, 15, 15, 15, 15, 15, 15, 15,
		15, 15, 15, 2, 2, 22, 2, 2,
		2, 23, 24, 2, 2, 2, 2, 25,
		26, 2, 27, 28, 29, 30, 24, 2,
		31, 26, 2, 32, 33, 21, 34, 35,
		36, 37, 38, 39, 40, 41, 42, 43,
		44, 45, 46, 47, 48, 49, 50, 51,
		52, 53, 54, 55, 56, 57, 58, 59,
		60, 61, 62, 63, 64, 65, 66, 67,
		68, 69, 70, 71, 72, 73, 74, 75,
		76, 77, 78, 79, 80, 81, 82, 83,
		84, 85, 86, 87, 88, 89, 90, 91,
		92, 93, 94, 95, 96, 97, 98, 99,
		100, 101, 102, 103, 104, 105, 106, 107,
		108, 109, 110, 111, 112, 113, 114, 115,
		116, 117, 118, 119, 120, 121, 122, 123,
		124, 125, 126, 127, 128, 129, 130, 131,
		132, 133, 134, 135, 136, 137, 138, 139,
		140, 141 
	};
	private int yy_nxt[][] = unpackFromString(142,63,
"-1,1,2,98,3,4,5,6,7,8,9,10,11,12,13,14,15,16:2,17,18,19,20,21,-1,22:6,23,-1,24,25,175,199,176,100,177,147,178,22,108,22,179,22:4,212,200,217,201,180,202,22:3,26,27,28,29,-1,1,-1:146,31,-1:46,32,-1:15,33,-1:62,34,-1:51,35,-1:10,36,-1:53,37,-1:8,38,39,-1:53,107,-1:58,40,-1:11,41,-1:56,99:2,-1:12,109,-1:25,109,-1:22,16:3,-1:65,42,43,-1:62,44,-1:62,45,46,-1:55,22:3,-1:6,22:6,-1:4,22:24,-1:26,47,-1:62,50,-1:37,51,-1:24,54,-1:62,55,-1:56,22:3,-1:6,22:6,-1:4,22:18,187,22:5,-1:20,53:3,-1:6,53:3,-1:7,53:6,-1:22,85:2,-1,85,-1,85:27,-1,85:30,-1:16,148:2,-1:61,90:3,-1:6,90:3,-1:7,90:6,-1:38,149:2,-1:61,96:3,-1:6,96:3,-1:7,96:6,-1:44,30,-1:56,99:2,-1:61,22:3,-1:6,22:6,-1:4,22:13,48,22:10,-1:19,84,-1:68,121,-1:55,52,-1:64,22:3,-1:6,22:6,-1:4,22:5,49,22:6,112,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:15,56,22:8,-1:4,83:2,2,83:7,101,83:52,-1:16,22:3,-1:6,22:6,-1:4,22:17,57,22:6,-1:4,85:2,-1,85,86,85:27,115,85:30,-1:16,22:3,-1:6,22:6,-1:4,22:13,58,22:10,-1:4,87:2,-1,87,88,87:2,88,87:8,89:2,87:6,88,87:7,88,87:3,88,87:3,88,87:6,88,87:2,88,87,88,87,88,87,102,87:6,-1:16,22:3,-1:6,22:6,-1:4,22:15,59,22:8,-1:4,91:2,-1,91:4,92,91:13,104,91:10,119,91:30,-1:16,22:3,-1:6,22:6,-1:4,22:4,60,22:19,-1:4,93:2,-1,93,94,93:2,94,93:8,95:2,93:6,94,93:7,94,93:3,94,93:3,94,93:6,94,93:2,94,93,94,93,94,93,105,93:6,-1:16,22:3,-1:6,22:6,-1:4,22:11,61,22:12,-1:30,123,-1:52,22:3,-1:6,22:6,-1:4,22:13,62,22:10,-1:33,125,-1:49,22:3,-1:6,22:6,-1:4,22:6,63,22:17,-1:31,127,-1:51,22:3,-1:6,22:6,-1:4,22:3,64,22:20,-1:27,129,-1:55,22:3,-1:6,22:6,-1:4,22:9,65,22:14,-1:27,97,-1:55,22:3,-1:6,22:6,-1:4,22:17,66,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:17,67,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:17,68,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:12,69,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:4,70,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:4,71,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:12,72,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:12,73,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:3,74,22:20,-1:20,22:3,-1:6,22:6,-1:4,22:5,75,22:18,-1:20,22:3,-1:6,22:6,-1:4,22:2,76,22:21,-1:20,22:3,-1:6,22:6,-1:4,22:17,77,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:5,78,22:18,-1:20,22:3,-1:6,22:6,-1:4,22:4,79,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:15,80,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:3,81,22:20,-1:20,22:3,-1:6,22:6,-1:4,22:4,82,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:10,183,22:2,110,22:10,-1:20,103:2,-1:61,106:2,-1:61,22:3,-1:6,22:6,-1:4,22:17,114,22:6,-1:20,22:3,-1:6,22:6,-1:4,116,22:23,-1:20,22:3,-1:6,22:6,-1:4,22:16,118,22:7,-1:20,22:3,-1:6,22:6,-1:4,22:18,120,22:5,-1:20,22:3,-1:6,22:6,-1:4,22:17,122,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:12,124,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:8,126,22,216,22:13,-1:20,22:3,-1:6,22:6,-1:4,128,22:23,-1:20,22:3,-1:6,22:6,-1:4,22:16,130,208,22:6,-1:20,22:3,-1:6,22:6,-1:4,131,22:23,-1:20,22:3,-1:6,22:6,-1:4,22:15,132,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:13,133,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:10,134,22:13,-1:20,22:3,-1:6,22:6,-1:4,22:10,135,22:13,-1:20,22:3,-1:6,22:6,-1:4,22:15,136,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:15,137,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:4,138,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:13,139,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:8,140,22:15,-1:20,22:3,-1:6,22:6,-1:4,22:2,141,22:21,-1:20,22:3,-1:6,22:6,-1:4,22:4,142,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:18,143,22:5,-1:20,22:3,-1:6,22:6,-1:4,22:4,144,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:4,145,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:10,146,22:13,-1:20,22:3,-1:6,22:6,-1:4,22:18,150,22:5,-1:20,22:3,-1:6,22:6,-1:4,22:7,151,22:5,182,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:10,152,22,153,22:8,203,22:2,-1:20,22:3,-1:6,22:6,-1:4,22:13,154,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:13,155,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:13,156,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:4,157,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:12,158,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:13,159,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:13,160,22:10,-1:20,22:3,-1:6,22:6,-1:4,22:8,161,22:7,215,22:7,-1:20,22:3,-1:6,22:6,-1:4,22:8,162,22:15,-1:20,22:3,-1:6,22:6,-1:4,22,163,22:22,-1:20,22:3,-1:6,22:6,-1:4,22:4,164,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:18,165,22:5,-1:20,22:3,-1:6,22:6,-1:4,22:12,166,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:4,167,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:17,168,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:18,169,22:5,-1:20,22:3,-1:6,22:6,-1:4,22:3,170,22:20,-1:20,22:3,-1:6,22:6,-1:4,22:12,171,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:17,172,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:12,173,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:8,174,22:15,-1:20,22:3,-1:6,22:6,-1:4,22:15,181,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:7,184,205,22:8,206,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:12,185,22:11,-1:20,22:3,-1:6,22:6,-1:4,22:7,186,22:16,-1:20,22:3,-1:6,22:6,-1:4,22:17,188,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:6,214,22:10,189,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:6,190,22:16,191,-1:20,22:3,-1:6,22:6,-1:4,192,22:14,193,22:8,-1:20,22:3,-1:6,22:6,-1:4,22:4,194,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:8,195,22:15,-1:20,22:3,-1:6,22:6,-1:4,22:16,196,22:7,-1:20,22:3,-1:6,22:6,-1:4,22:6,197,22:17,-1:20,22:3,-1:6,22:6,-1:4,22:17,198,22:6,-1:20,22:3,-1:6,22:6,-1:4,22:4,204,22:19,-1:20,22:3,-1:6,22:6,-1:4,22:14,207,22:9,-1:20,22:3,-1:6,22:6,-1:4,22:8,209,22:15,-1:20,22:3,-1:6,22:6,-1:4,22:8,210,22:15,-1:20,22:3,-1:6,22:6,-1:4,211,22:23,-1:20,22:3,-1:6,22:6,-1:4,22:22,213,22,-1:4");
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
						{string.setLength(0); yybegin(STRING);}
					case -4:
						break;
					case 4:
						{return tok(sym.MODULUS, null);}
					case -5:
						break;
					case 5:
						{return tok(sym.BITWISEAND, null);}
					case -6:
						break;
					case 6:
						{string.setLength(0); yybegin(CHAR);}
					case -7:
						break;
					case 7:
						{return tok(sym.LPAREN, null);}
					case -8:
						break;
					case 8:
						{return tok(sym.RPAREN, null);}
					case -9:
						break;
					case 9:
						{return tok(sym.TIMES, null);}
					case -10:
						break;
					case 10:
						{return tok(sym.PLUS, null);}
					case -11:
						break;
					case 11:
						{return tok(sym.COMMA, null);}
					case -12:
						break;
					case 12:
						{return tok(sym.MINUS, null);}
					case -13:
						break;
					case 13:
						{return tok(sym.PERIOD, null);}
					case -14:
						break;
					case 14:
						{return tok(sym.DIVIDE, null);}
					case -15:
						break;
					case 15:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext(),8)); }
					case -16:
						break;
					case 16:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -17:
						break;
					case 17:
						{return tok(sym.COLON, null);}
					case -18:
						break;
					case 18:
						{return tok(sym.SEMICOLON, null);}
					case -19:
						break;
					case 19:
						{return tok(sym.LT, null);}
					case -20:
						break;
					case 20:
						{return tok(sym.ASSIGN, null);}
					case -21:
						break;
					case 21:
						{return tok(sym.GT, null);}
					case -22:
						break;
					case 22:
						{ return tok(sym.ID, yytext()); }
					case -23:
						break;
					case 23:
						{return tok(sym.LBRACK, null);}
					case -24:
						break;
					case 24:
						{return tok(sym.RBRACK, null);}
					case -25:
						break;
					case 25:
						{return tok(sym.BWISEXOR, null);}
					case -26:
						break;
					case 26:
						{return tok(sym.LBRACE, null);}
					case -27:
						break;
					case 27:
						{return tok(sym.BWISEOR, null);}
					case -28:
						break;
					case 28:
						{return tok(sym.RBRACE, null);}
					case -29:
						break;
					case 29:
						{return tok(sym.TILDE, null);}
					case -30:
						break;
					case 30:
						{return tok(sym.NEQ, null);}
					case -31:
						break;
					case 31:
						{return tok(sym.MODASSIGN, null);}
					case -32:
						break;
					case 32:
						{return tok(sym.AND, null);}
					case -33:
						break;
					case 33:
						{return tok(sym.BWISEANDASSIGN, null);}
					case -34:
						break;
					case 34:
						{return tok(sym.MULASSIGN, null);}
					case -35:
						break;
					case 35:
						{return tok(sym.INCREMENT, null);}
					case -36:
						break;
					case 36:
						{return tok(sym.ADDASSIGN, null);}
					case -37:
						break;
					case 37:
						{return tok(sym.DECREMENT, null);}
					case -38:
						break;
					case 38:
						{return tok(sym.SUBASSIGN, null);}
					case -39:
						break;
					case 39:
						{return tok(sym.ARROW, null);}
					case -40:
						break;
					case 40:
						{ yybegin(COMMENT); }
					case -41:
						break;
					case 41:
						{return tok(sym.DIVASSIGN, null);}
					case -42:
						break;
					case 42:
						{return tok(sym.LSHIFT, null);}
					case -43:
						break;
					case 43:
						{return tok(sym.LE, null);}
					case -44:
						break;
					case 44:
						{return tok(sym.EQ, null);}
					case -45:
						break;
					case 45:
						{return tok(sym.GE, null);}
					case -46:
						break;
					case 46:
						{return tok(sym.RSHIFT, null);}
					case -47:
						break;
					case 47:
						{return tok(sym.BWISEXORASSIGN, null);}
					case -48:
						break;
					case 48:
						{return tok(sym.DO, null);}
					case -49:
						break;
					case 49:
						{return tok(sym.IF, null);}
					case -50:
						break;
					case 50:
						{return tok(sym.BWISEORASSIGN, null);}
					case -51:
						break;
					case 51:
						{return tok(sym.OR, null);}
					case -52:
						break;
					case 52:
						{return tok(sym.ELIPSES, null);}
					case -53:
						break;
					case 53:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
					case -54:
						break;
					case 54:
						{return tok(sym.LSHIFTASSIGN, null);}
					case -55:
						break;
					case 55:
						{return tok(sym.RSHIFTASSIGN, null);}
					case -56:
						break;
					case 56:
						{return tok(sym.FOR, null);}
					case -57:
						break;
					case 57:
						{return tok(sym.INT, null);}
					case -58:
						break;
					case 58:
						{return tok(sym.AUTO, null);}
					case -59:
						break;
					case 59:
						{return tok(sym.CHAR, null);}
					case -60:
						break;
					case 60:
						{return tok(sym.ELSE, null);}
					case -61:
						break;
					case 61:
						{return tok(sym.ENUM, null);}
					case -62:
						break;
					case 62:
						{return tok(sym.GOTO, null);}
					case -63:
						break;
					case 63:
						{return tok(sym.LONG, null);}
					case -64:
						break;
					case 64:
						{return tok(sym.VOID, null);}
					case -65:
						break;
					case 65:
						{return tok(sym.BREAK, null);}
					case -66:
						break;
					case 66:
						{return tok(sym.CONST, null);}
					case -67:
						break;
					case 67:
						{return tok(sym.FLOAT, null);}
					case -68:
						break;
					case 68:
						{return tok(sym.SHORT, null);}
					case -69:
						break;
					case 69:
						{return tok(sym.UNION, null);}
					case -70:
						break;
					case 70:
						{return tok(sym.WHILE, null);}
					case -71:
						break;
					case 71:
						{return tok(sym.DOUBLE, null);}
					case -72:
						break;
					case 72:
						{return tok(sym.EXTERN, null);}
					case -73:
						break;
					case 73:
						{return tok(sym.RETURN, null);}
					case -74:
						break;
					case 74:
						{return tok(sym.SIGNED, null);}
					case -75:
						break;
					case 75:
						{return tok(sym.SIZEOF, null);}
					case -76:
						break;
					case 76:
						{return tok(sym.STATIC, null);}
					case -77:
						break;
					case 77:
						{return tok(sym.STRUCT, null);}
					case -78:
						break;
					case 78:
						{return tok(sym.TYPEDEF, null);}
					case -79:
						break;
					case 79:
						{return tok(sym.CONTINUE, null);}
					case -80:
						break;
					case 80:
						{return tok(sym.REGISTER, null);}
					case -81:
						break;
					case 81:
						{return tok(sym.UNSIGNED, null);}
					case -82:
						break;
					case 82:
						{return tok(sym.VOLATILE, null);}
					case -83:
						break;
					case 83:
						{ /* skip comment content */ }
					case -84:
						break;
					case 84:
						{ yybegin(YYINITIAL); }
					case -85:
						break;
					case 85:
						{string.append(yytext());}
					case -86:
						break;
					case 86:
						{
    yybegin(YYINITIAL);
    return tok(sym.STRING_LITERAL, string.toString());
  }
					case -87:
						break;
					case 87:
						{ 
  err("Invalid escape sequence: " + yytext()); 
  }
					case -88:
						break;
					case 88:
						{ appendEscapeSequence(yytext().charAt(1)); }
					case -89:
						break;
					case 89:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -90:
						break;
					case 90:
						{
  String hexStr = yytext().substring(2);
  int value = Integer.parseInt(hexStr, 16);
  string.append((char)value);
}
					case -91:
						break;
					case 91:
						{string.append(yytext());}
					case -92:
						break;
					case 92:
						{
    if (string.length() == 0) {
      err("empty character literal");
      yybegin(YYINITIAL);
      return tok(sym.error, null);
    }
    yybegin(YYINITIAL);
    return tok(sym.CHAR_LITERAL, string.toString());
  }
					case -93:
						break;
					case 93:
						{err("Invalid escape sequence: " + yytext());}
					case -94:
						break;
					case 94:
						{ appendEscapeSequence(yytext().charAt(1)); }
					case -95:
						break;
					case 95:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -96:
						break;
					case 96:
						{
  String hexStr = yytext().substring(2);
  int value = Integer.parseInt(hexStr, 16);
  string.append((char)value);
}
					case -97:
						break;
					case 97:
						{
    err("Unterminated character literal at end of file");
    yybegin(YYINITIAL);
    return tok(sym.EOF, null);
}
					case -98:
						break;
					case 99:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext(),8)); }
					case -99:
						break;
					case 100:
						{ return tok(sym.ID, yytext()); }
					case -100:
						break;
					case 101:
						{ /* skip comment content */ }
					case -101:
						break;
					case 102:
						{ 
  err("Invalid escape sequence: " + yytext()); 
  }
					case -102:
						break;
					case 103:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -103:
						break;
					case 104:
						{string.append(yytext());}
					case -104:
						break;
					case 105:
						{err("Invalid escape sequence: " + yytext());}
					case -105:
						break;
					case 106:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -106:
						break;
					case 108:
						{ return tok(sym.ID, yytext()); }
					case -107:
						break;
					case 110:
						{ return tok(sym.ID, yytext()); }
					case -108:
						break;
					case 112:
						{ return tok(sym.ID, yytext()); }
					case -109:
						break;
					case 114:
						{ return tok(sym.ID, yytext()); }
					case -110:
						break;
					case 116:
						{ return tok(sym.ID, yytext()); }
					case -111:
						break;
					case 118:
						{ return tok(sym.ID, yytext()); }
					case -112:
						break;
					case 120:
						{ return tok(sym.ID, yytext()); }
					case -113:
						break;
					case 122:
						{ return tok(sym.ID, yytext()); }
					case -114:
						break;
					case 124:
						{ return tok(sym.ID, yytext()); }
					case -115:
						break;
					case 126:
						{ return tok(sym.ID, yytext()); }
					case -116:
						break;
					case 128:
						{ return tok(sym.ID, yytext()); }
					case -117:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -118:
						break;
					case 131:
						{ return tok(sym.ID, yytext()); }
					case -119:
						break;
					case 132:
						{ return tok(sym.ID, yytext()); }
					case -120:
						break;
					case 133:
						{ return tok(sym.ID, yytext()); }
					case -121:
						break;
					case 134:
						{ return tok(sym.ID, yytext()); }
					case -122:
						break;
					case 135:
						{ return tok(sym.ID, yytext()); }
					case -123:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -124:
						break;
					case 137:
						{ return tok(sym.ID, yytext()); }
					case -125:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -126:
						break;
					case 139:
						{ return tok(sym.ID, yytext()); }
					case -127:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -128:
						break;
					case 141:
						{ return tok(sym.ID, yytext()); }
					case -129:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 143:
						{ return tok(sym.ID, yytext()); }
					case -131:
						break;
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -132:
						break;
					case 145:
						{ return tok(sym.ID, yytext()); }
					case -133:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -134:
						break;
					case 147:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 148:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -136:
						break;
					case 149:
						{
  String octalEsc = yytext().substring(1);
  int value = Integer.parseInt(octalEsc, 8);
  string.append((char)value);
}
					case -137:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 151:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 153:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 166:
						{ return tok(sym.ID, yytext()); }
					case -154:
						break;
					case 167:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 173:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 174:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 175:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 176:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 177:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 178:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 179:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 180:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 181:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 182:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 183:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 184:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					case 185:
						{ return tok(sym.ID, yytext()); }
					case -173:
						break;
					case 186:
						{ return tok(sym.ID, yytext()); }
					case -174:
						break;
					case 187:
						{ return tok(sym.ID, yytext()); }
					case -175:
						break;
					case 188:
						{ return tok(sym.ID, yytext()); }
					case -176:
						break;
					case 189:
						{ return tok(sym.ID, yytext()); }
					case -177:
						break;
					case 190:
						{ return tok(sym.ID, yytext()); }
					case -178:
						break;
					case 191:
						{ return tok(sym.ID, yytext()); }
					case -179:
						break;
					case 192:
						{ return tok(sym.ID, yytext()); }
					case -180:
						break;
					case 193:
						{ return tok(sym.ID, yytext()); }
					case -181:
						break;
					case 194:
						{ return tok(sym.ID, yytext()); }
					case -182:
						break;
					case 195:
						{ return tok(sym.ID, yytext()); }
					case -183:
						break;
					case 196:
						{ return tok(sym.ID, yytext()); }
					case -184:
						break;
					case 197:
						{ return tok(sym.ID, yytext()); }
					case -185:
						break;
					case 198:
						{ return tok(sym.ID, yytext()); }
					case -186:
						break;
					case 199:
						{ return tok(sym.ID, yytext()); }
					case -187:
						break;
					case 200:
						{ return tok(sym.ID, yytext()); }
					case -188:
						break;
					case 201:
						{ return tok(sym.ID, yytext()); }
					case -189:
						break;
					case 202:
						{ return tok(sym.ID, yytext()); }
					case -190:
						break;
					case 203:
						{ return tok(sym.ID, yytext()); }
					case -191:
						break;
					case 204:
						{ return tok(sym.ID, yytext()); }
					case -192:
						break;
					case 205:
						{ return tok(sym.ID, yytext()); }
					case -193:
						break;
					case 206:
						{ return tok(sym.ID, yytext()); }
					case -194:
						break;
					case 207:
						{ return tok(sym.ID, yytext()); }
					case -195:
						break;
					case 208:
						{ return tok(sym.ID, yytext()); }
					case -196:
						break;
					case 209:
						{ return tok(sym.ID, yytext()); }
					case -197:
						break;
					case 210:
						{ return tok(sym.ID, yytext()); }
					case -198:
						break;
					case 211:
						{ return tok(sym.ID, yytext()); }
					case -199:
						break;
					case 212:
						{ return tok(sym.ID, yytext()); }
					case -200:
						break;
					case 213:
						{ return tok(sym.ID, yytext()); }
					case -201:
						break;
					case 214:
						{ return tok(sym.ID, yytext()); }
					case -202:
						break;
					case 215:
						{ return tok(sym.ID, yytext()); }
					case -203:
						break;
					case 216:
						{ return tok(sym.ID, yytext()); }
					case -204:
						break;
					case 217:
						{ return tok(sym.ID, yytext()); }
					case -205:
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
