/* Test edge cases and corner cases */

/* Empty compound statement */
{
}

/* Nested compound statements */
{
    {
        {
            var int deeply_nested;
        }
    }
}

/* Complex pointer and array combinations */
var int (*funcptr)(int, int);
var int* (*arrfuncptr[10])(float);

/* Multiple levels of indirection */
var int*** trippleptr;
var char**** quadptr;

/* Empty parameter lists */
fun void noparams();

/* Variadic functions (with ellipsis) */
fun int variadic(int first, ...);
fun void varargs(char* fmt, int x, ...);

/* All bitfield combinations */
var const volatile static int cvs;
var extern register int er;

/* Enum with single value */
enum single {
    ONLY_ONE
};

/* Nested expressions */
((((x + y) * z) - w) / v);

/* Complex assignment chains */
x = y = z = 0;

/* Multiple array dimensions with expressions */
var int complex_arr[5][10][15];

/* Struct with only one field */
struct singleton {
    int value;
};

/* Union with same types */
union redundant {
    int a;
    int b;
    int c;
};

/* Empty for loop components */
for (;;) {
    break;
}

/* For with complex expressions */
for (i = 0, j = 10; i < j && i < 100; i++, j--) {
    continue;
}

/* Deeply nested if-else */
if (a)
    if (b)
        if (c)
            if (d)
                x = 1;
            else
                x = 2;
        else
            x = 3;
    else
        x = 4;
else
    x = 5;

/* Cast in complex expression */
(int)((float)x + (double)y);

/* Function call with complex arguments */
foo(bar(x), baz(y, z), qux());

/* Field access chains */
obj.field1.field2.field3;
ptr->member->submember->value;

/* Mixed field access */
obj.ptr->field.value;

/* Array of struct with field access */
arr[i].field;
arr[i]->member;
