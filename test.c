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

/* Assignment operators */
var int assign_test = 10;
assign_test += 5;
assign_test -= 2;
assign_test *= 3;
assign_test /= 2;

/* Punctuation and delimiters */
var int array[100];
var int matrix[10][20];

fun simple_function() {
    return 42;
}

fun parameterized_function(a, b, c) {
    var local_var = a + b * c;
    return local_var;
}

fun complex_function(x, y) {
    var temp = 0;
    
    if (x > y) {
        temp = x;
    } else {
        temp = y;
    }
    
    while (temp > 0) {
        temp = temp - 1;
    }
    
    for (var i = 0; i < 10; i = i + 1) {
        temp = temp + i;
    }
    
    return temp;
}

/* Control flow comprehensive test */
fun control_flow_test() {
    var i = 0;
    var j = 10;
    var result = 0;
    
    
    if (i == 0) {
        result = 1;
    } else if (i == 1) {
        result = 2;
    } else if (i == 2) {
        result = 3;
    } else {
        result = 4;
    }
    
 
    while (i < 5) {
        result = result + i;
        i = i + 1;
    }
    

    for (j = 0; j < 3; j = j + 1) {
        result = result * 2;
    }
    
    return result;
}

/* Expression complexity test */
fun expression_test() {
    var a = 10;
    var b = 20;
    var c = 30;
    
    var complex1 = (a + b) * c - (a * b) / c;
    var complex2 = a * (b + c) / (a - b + c);
    var complex3 = ((a + b) * (c - a)) / ((b * c) + a);
    
    var logical = (a < b) && (b < c) || (a != c);
    var mixed = (a + b > c) ? a : b;
    
    return complex1 + complex2 + complex3 + logical + mixed;
}

/* Array and indexing test */
fun array_test() {
    var numbers[50];
    var i = 0;
    var sum = 0;
    

    for (i = 0; i < 50; i = i + 1) {
        numbers[i] = i * i;
    }

    for (i = 0; i < 50; i = i + 1) {
        sum = sum + numbers[i];
    }
    
    return sum;
}

/* Nested function calls */
fun nested_calls_test() {
    var result1 = simple_function();
    var result2 = parameterized_function(1, 2, 3);
    var result3 = complex_function(result1, result2);
    
    return result3;
}

/* Main function with comprehensive testing */
fun main() {
    var test_results[10];
    var i = 0;
    

    test_results[0] = simple_function();
    test_results[1] = parameterized_function(5, 10, 15);
    test_results[2] = complex_function(100, 50);
    test_results[3] = control_flow_test();
    test_results[4] = expression_test();
    test_results[5] = array_test();
    test_results[6] = nested_calls_test();
    
   
    printf("Results:\n");
    for (i = 0; i < 7; i = i + 1) {
        printf("Test %d: %d\n", i, test_results[i]);
    }
    
    
    printf("All escapes: \\n\\t\\r\\b\\f\\v\\\\\\'\\\"");
    printf("Octal: \\101\\102\\103\\000\\040\\012");
    printf("Hex: \\x41\\x42\\x43\\x00\\x20\\x0A");
    
    return 0;
}