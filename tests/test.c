/* Multi-line comment testing */

/* Keywords and identifiers test */
var int count = 42;
var char* message = "Hello World";
var int result = 0;
var int test_var = 999;
var int _underscore = 123;
var int mixedCase123 = 456;

/* String literal comprehensive test */
var char* str1 = "";
var char* str2 = "Simple string";
var char* str3 = "String with \"quotes\" inside";
var char* str4 = "String with 'single quotes'";
var char* str5 = "Escaped chars: \\ \n \t \r \b \f \v";
var char* str6 = "Mixed: Hello\tWorld\nNext line";

/* Octal escape sequences in strings */
var char* octal1 = "Octal: \101\102\103";
var char* octal2 = "Null: \000 and space: \040";
var char* octal3 = "Numbers: \060\061\062";

/* Hexadecimal escape sequences in strings */
var char* hex1 = "Hex: \x41\x42\x43";
var char* hex2 = "Hex null: \x00 and space: \x20";
var char* hex3 = "Hex digits: \x30\x31\x32";

/* Character literal comprehensive test */
var char ch1 = 'a';
var char ch2 = 'Z';
var char ch3 = '5';
var char ch4 = '@';
var char ch5 = ' ';
var char ch6 = '!';

/* Escaped character literals */
var char esc1 = '\n';
var char esc2 = '\t';
var char esc3 = '\r';
var char esc4 = '\b';
var char esc5 = '\f';
var char esc6 = '\v';
var char esc7 = '\\';
var char esc8 = '\'';
var char esc9 = '\"';

/* Octal character literals */
var char oct_ch1 = '\101';
var char oct_ch2 = '\102';
var char oct_ch3 = '\040';
var char oct_ch4 = '\000';
var char oct_ch5 = '\012';

/* Hexadecimal character literals */
var char hex_ch1 = '\x41';
var char hex_ch2 = '\x42';
var char hex_ch3 = '\x20';
var char hex_ch4 = '\x00';
var char hex_ch5 = '\x0A';

/* Integer literals test */
var int int1 = 0;
var int int2 = 123;
var int int3 = 999999;
var int int4 = 007;
var int int5 = 42;

/* Operators test */
var int op_result1 = 5 + 3;
var int op_result2 = 10 - 4;
var int op_result3 = 6 * 7;
var int op_result4 = 20 / 4;
var int op_result5 = 15 % 4;

/* Comparison operators */
var int comp1 = (5 == 5);
var int comp2 = (3 != 4);
var int comp3 = (7 < 10);
var int comp4 = (8 <= 8);
var int comp5 = (12 > 9);
var int comp6 = (15 >= 15);

/* Logical operators */
var int logic1 = (1 && 2);
var int logic2 = (0 || 1);
var int logic3 = !0;

/* Array declarations with proper syntax */
var int[100] array;
var int[10][20] matrix;

/* Pointer declarations */
var int* intPtr;
var char* charPtr;
var int** doublePtrInt;

/* Typedef declarations */
typedef int Integer;
typedef char* String;
typedef int[10] IntArray;

/* Struct declarations */
struct Point {
    int x;
    int y;
}

struct Rectangle {
    int width;
    int height;
}

/* Union declarations */
union Value {
    int i;
    float f;
    char c;
}

/* Enum declarations */
enum Color { RED, GREEN, BLUE }
enum Status { OK, ERROR, PENDING }

/* Function declarations (prototypes) */
fun int simple_function();
fun int parameterized_function(int, int, int);
fun int complex_function(int, int);

/* Function definitions */
fun int simple_function() {
    return 42;
}

fun int parameterized_function(int a, int b, int c) {
    var int local_var = a + b * c;
    return local_var;
}

fun int complex_function(int x, int y) {
    var int temp = 0;
    
    if (x > y) {
        temp = x;
    } else {
        temp = y;
    }
    
    while (temp > 0) {
        temp = temp - 1;
    }
    
    return temp;
}

fun int main() {
    var int[10] test_results;
    var int i = 0;
    
    test_results[0] = simple_function();
    test_results[1] = parameterized_function(5, 10, 15);
    test_results[2] = complex_function(100, 50);
    
    return 0;
}
