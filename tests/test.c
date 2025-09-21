/* Multi-line comment testing
   Multiple lines with various symbols: !@#$%^&*()
   Testing nested /* comments */ within comments
   End of multi-line comment */

// Single line comment with symbols: +=*/<>!=
// Another single line comment

/* Keywords and identifiers test */
var count = 42;
var message = "Hello World";
var result = 0;
var test_var = 999;
var _underscore = 123;
var mixedCase123 = 456;

/* String literal comprehensive test */
var str1 = "";
var str2 = "Simple string";
var str3 = "String with \"quotes\" inside";
var str4 = "String with 'single quotes'";
var str5 = "Escaped chars: \\ \n \t \r \b \f \v";
var str6 = "Mixed: Hello\tWorld\nNext line";

/* Octal escape sequences in strings */
var octal1 = "Octal: \101\102\103";
var octal2 = "Null: \000 and space: \040";
var octal3 = "Numbers: \060\061\062";

/* Hexadecimal escape sequences in strings */
var hex1 = "Hex: \x41\x42\x43";
var hex2 = "Hex null: \x00 and space: \x20";
var hex3 = "Hex digits: \x30\x31\x32";

/* Character literal comprehensive test */
var ch1 = 'a';
var ch2 = 'Z';
var ch3 = '5';
var ch4 = '@';
var ch5 = ' ';
var ch6 = '!';

/* Escaped character literals */
var esc1 = '\n';
var esc2 = '\t';
var esc3 = '\r';
var esc4 = '\b';
var esc5 = '\f';
var esc6 = '\v';
var esc7 = '\\';
var esc8 = '\'';
var esc9 = '\"';

/* Octal character literals */
var oct_ch1 = '\101';
var oct_ch2 = '\102';
var oct_ch3 = '\040';
var oct_ch4 = '\000';
var oct_ch5 = '\012';

/* Hexadecimal character literals */
var hex_ch1 = '\x41';
var hex_ch2 = '\x42';
var hex_ch3 = '\x20';
var hex_ch4 = '\x00';
var hex_ch5 = '\x0A';

/* Integer literals test */
var int1 = 0;
var int2 = 123;
var int3 = 999999;
var int4 = 007;
var int5 = 42;

/* Operators test */
var op_result1 = 5 + 3;
var op_result2 = 10 - 4;
var op_result3 = 6 * 7;
var op_result4 = 20 / 4;
var op_result5 = 15 % 4;

/* Comparison operators */
var comp1 = (5 == 5);
var comp2 = (3 != 4);
var comp3 = (7 < 10);
var comp4 = (8 <= 8);
var comp5 = (12 > 9);
var comp6 = (15 >= 15);

/* Logical operators */
var logic1 = (1 && 2);
var logic2 = (0 || 1);
var logic3 = !0;

/* Assignment operators */
var assign_test = 10;
assign_test += 5;
assign_test -= 2;
assign_test *= 3;
assign_test /= 2;

/* Punctuation and delimiters */
var array[100];
var matrix[10][20];

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
    
    // If-else chain
    if (i == 0) {
        result = 1;
    } else if (i == 1) {
        result = 2;
    } else if (i == 2) {
        result = 3;
    } else {
        result = 4;
    }
    
    // While loop
    while (i < 5) {
        result = result + i;
        i = i + 1;
    }
    
    // For loop
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
    
    // Initialize array
    for (i = 0; i < 50; i = i + 1) {
        numbers[i] = i * i;
    }
    
    // Sum array elements
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
    
    // Test all functions
    test_results[0] = simple_function();
    test_results[1] = parameterized_function(5, 10, 15);
    test_results[2] = complex_function(100, 50);
    test_results[3] = control_flow_test();
    test_results[4] = expression_test();
    test_results[5] = array_test();
    test_results[6] = nested_calls_test();
    
    // Print results with various format strings
    printf("Results:\n");
    for (i = 0; i < 7; i = i + 1) {
        printf("Test %d: %d\n", i, test_results[i]);
    }
    
    // Test complex string with all escape sequences
    printf("All escapes: \\n\\t\\r\\b\\f\\v\\\\\\'\\\"");
    printf("Octal: \\101\\102\\103\\000\\040\\012");
    printf("Hex: \\x41\\x42\\x43\\x00\\x20\\x0A");
    
    return 0;
}