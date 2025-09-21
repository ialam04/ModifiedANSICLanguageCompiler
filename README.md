Team Members: Ibrahim Alam, Jackson Descant, and Jackson Thomas

Design Decisions:
- No support for "!" as we were told it was a bug from last semester
- No support for "?" as we were told since it was not included in sym.java we were free to add it or not add it
- No support for "\a" as it has no java counterpart and we were told we can avoid any esc sequences that do not have java counterparts

Strings/Chars:
- Strings/chars are handled by having a regex pattern that detects the quotations for the start of the string and then uses the string buffer to fill it. Strings have their own dedicated STRING state. Chars have their own dedicated CHAR state. The string/char is then handled according to the rules that are places on it like detecting different types of escape sequences, empty char(char error), EOF(string error) or invalid escape(error) sequences in the string/char. Then the string/char is finalized when finding the last quotation mark and the String buffer is rest by going back to the start state of the string/char rule.  

Control Characters/Escape Sequences:
- These are detected in the string/char and then sent to a helper method to implement them. The helper method recognizes the raw text from the C code to see what the escape sequence is, and then it appends the Java equivelant of the escape sequence to the current state of the string buffer (implementing the actual functionality of the escape sequence). Invalid escape sequences are detected and an error is thrown for them.

Comments:
- 

AI and Internet Use:
- Internet: JLex documentation
- AI: Explaining JLex Documentation and generating regex patterns 