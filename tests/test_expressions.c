/* Test file for expression parsing */

/* Simple arithmetic */
x + y * z;

/* Complex arithmetic with precedence */
(a + b) * (c - d) / e % f;

/* Bitwise operations */
x & y | z ^ w;

/* Shift operations */
a << 2;
b >> 3;

/* Logical operations */
x && y || z;

/* Relational operations */
a < b;
c > d;
e <= f;
g >= h;
i == j;
k != l;

/* Assignment operations */
x = 10;
y += 5;
z -= 3;
a *= 2;
b /= 4;
c %= 3;

/* Unary operations */
++x;
--y;
x++;
y--;
-z;
+w;
~a;
&b;
*c;

/* Array subscript */
arr[5];
matrix[i][j];

/* Function calls */
foo();
bar(x, y, z);
baz(a + b, c * d);

/* Field access */
obj.field;
ptr->member;

/* Cast expression */
(int)x;
(float*)ptr;

/* Sizeof */
sizeof(x);
sizeof(int);

/* Conditional/ternary not in grammar, but complex expression */
a + b * c - d / e;

/* Comma operator */
x = 1, y = 2, z = 3;
