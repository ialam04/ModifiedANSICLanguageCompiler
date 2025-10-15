/*
 * Comprehensive test file for C expressions and statements
 * Tests the parser implementation for Project 2
 */

// EXPRESSION TESTS
// ================

// Basic Literals
42;
'a';
"hello world";
x;

// Arithmetic Expressions
x + 5;
a - b * c;
x / y % z;
(a + b) * (c - d);

// Assignment Expressions
x = 5;
y += 10;
z -= 3;
a *= 2;
b /= 4;
c %= 7;
d <<= 2;
e >>= 1;
f &= 0xFF;
g |= 0x80;
h ^= 0xAA;

// Unary Expressions
++x;
--y;
x++;
y--;
+a;
-b;
~c;
*ptr;
&var;
sizeof(int);
sizeof x;

// Postfix Expressions
arr[5];
obj.field;
ptr->member;
func();
func(a, b, c);

// Cast Expressions
(int)x;
(char*)ptr;

// Logical and Bitwise Expressions
a && b;
x || y;
p & q;
m | n;
r ^ s;

// Relational Expressions
a < b;
x <= y;
p > q;
m >= n;
r == s;
t != u;

// Shift Expressions
x << 2;
y >> 3;

// Conditional Expression (ternary)
x > 0 ? x : -x;

// Complex Expressions
a[i].field->ptr + (b * c) - func(x, y);
*(ptr + offset) = value++;

// STATEMENT TESTS
// ===============

// Expression Statements
x = 5;
func(a, b);
++counter;

// Compound Statements
{
    x = 1;
    y = 2;
}

{
    int a;
    a = 5;
    func(a);
}

// If Statements
if (x > 0)
    y = 1;

if (a == b)
    x = 1;
else
    x = 2;

// While Loops
while (x < 10)
    x++;

while (condition) {
    process();
    update();
}

// Do-While Loops
do
    x++;
while (x < limit);

do {
    read_input();
    validate();
} while (more_data);

// For Loops
for (i = 0; i < n; i++)
    process(i);

for (;;)
    infinite_loop();

for (init(); test(); update()) {
    body();
}

// Jump Statements
break;
continue;
return;
return value;
goto label;

// Labeled Statements
label:
    statement;

error_handler:
    cleanup();

case 1:
    handle_one();

case 2:
    handle_two();

default:
    handle_default();

// Complex Statement Combinations
if (condition) {
    for (i = 0; i < max; i++) {
        if (array[i] == target) {
            result = i;
            break;
        }
    }
} else {
    while (retry_count < MAX_RETRIES) {
        if (attempt()) {
            success = 1;
            break;
        }
        retry_count++;
    }
}

// Nested Compound Statements
{
    int local_var;
    {
        int nested_var;
        nested_var = compute();
        local_var = nested_var * 2;
    }
    result = local_var;
}