/* Test file for declaration parsing */

/* Simple variable declarations */
var int x;
var char c;
var float f;
var double d;

/* Variable with initialization */
var int initialized = 42;

/* Variables with bitfields */
var const int cx = 10;
var volatile int vx;
var static int sx;
var extern int ex;
var auto int ax;
var register int rx;

/* Multiple bitfields */
var const volatile int cvx;
var static const int scx;

/* Pointer declarations */
var int* ptr;
var int** pptr;
var char* str;

/* Array declarations */
var int arr[10];
var int matrix[5][10];
var char buffer[];

/* Pointer to array and array of pointers */
var int* ptrarr[5];
var int (*arrptr)[10];

/* Typedef declarations */
typedef int myint;
typedef char* string;
typedef struct point Point;

/* Struct declarations */
struct point {
    int x;
    int y;
};

struct person {
    char* name;
    int age;
    float height;
};

/* Union declarations */
union data {
    int i;
    float f;
    char c;
};

/* Enum declarations */
enum color {
    RED,
    GREEN,
    BLUE
};

enum status {
    OK = 0,
    ERROR = 1,
    PENDING = 2
};

/* Function declarations (prototypes) */
fun int add(int a, int b);
fun void print(char* msg);
fun float compute(float x, float y, float z);

/* Function with no parameters */
fun int getvalue();

/* Function declarations with bitfields */
fun static int helper(int x);
fun extern void external();

/* Function definitions */
fun int square(int n) {
    return n * n;
}

fun void increment(int* ptr) {
    *ptr = *ptr + 1;
}

fun int factorial(int n) {
    if (n <= 1)
        return 1;
    else
        return n * factorial(n - 1);
}

/* Complex type declarations */
var int* arrayofptrs[10];
var const char* const constptrtoconst;
var static volatile int svi;
